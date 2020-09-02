package com.co.controller;

import com.co.dto.ErrorDTO;
import com.co.dto.ResponseMinSaludDTO;
import com.co.entities.ParametroGeneral;
import com.co.entities.RespuestaSATARL;
import com.co.exception.MinSaludBusinessException;
import com.co.singleton.ConfiguracionSingleton;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.co.dto.RequestBodyDTO;
import com.co.dto.RequestFormPostDTO;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.co.utils.SisafitraConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 *
 */
public class BaseController
{

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    List<ParametroGeneral> parametros;

    public <T> Object responseFromPostRequest(RequestBodyDTO request, Class<T> type) throws IOException, NoSuchFieldException, IllegalAccessException {
        HttpPost post = new HttpPost(request.getUrl());
        request.getHeaders().forEach(post::setHeader);
        post.setHeader("Content-Type", "application/json; charset=utf-8");
        post.setEntity(new ByteArrayEntity(request.getBody().getBytes()));
        return sendRequest(post, type);
    }

    public <T> Object responseFromPostRequestWithPossibleMappingError(RequestBodyDTO request, Class<T> type) throws IOException, NoSuchFieldException, IllegalAccessException {
        HttpPost post = new HttpPost(request.getUrl());
        request.getHeaders().forEach(post::setHeader);
        post.setEntity(new ByteArrayEntity(request.getBody().getBytes()));
        post.setHeader("Content-Type", "application/json; charset=utf-8");
        return sendRequestWithMappingError(post, type);
    }

    public <T> Object responseFromPostListRequest(RequestBodyDTO request, Class<T> type) throws IOException, NoSuchFieldException, IllegalAccessException {
        HttpPost post = new HttpPost(request.getUrl());
        request.getHeaders().forEach(post::setHeader);
        post.setEntity(new ByteArrayEntity(request.getBody().getBytes()));
        post.setHeader("Content-Type", "application/json; charset=utf-8");
        return sendRequestList(post, type);
    }

    public <T> Object responseFromPostFormRequest(RequestFormPostDTO request, Class<T> type) throws IOException, NoSuchFieldException, IllegalAccessException {
        HttpPost post = new HttpPost(request.getUrl());
        request.getHeaders().forEach(post::setHeader);
        post.setEntity(new UrlEncodedFormEntity(request.getParams(), "UTF-8"));
        post.setHeader("Content-Type", "application/json; charset=utf-8");
        return sendRequest(post, type);
    }

    public <T> Object responseFromGetRequest(String url, Map<String, String> headers, Class<T> type) throws IOException, NoSuchFieldException, IllegalAccessException {
        HttpGet get = new HttpGet(url);
        headers.forEach(get::setHeader);
        return sendRequest(get, type);
    }


    public String mapperBody(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

    public LocalDate parseStringToLocalDate(String date)
    {
        return LocalDate.parse(date);
    }

    public java.sql.Date localDateToDate(LocalDate date)
    {
        return (Date) java.util.Date.from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static LocalDate getLocalDate(final LocalDate localDate) {
        final LocalDate localDateStartingWithNextMonth = localDate.with(TemporalAdjusters.firstDayOfNextMonth()).plusMonths(1);
        return IntStream.range(0, 1).boxed().map(localDateStartingWithNextMonth::plusDays).findAny().get();
    }

    public ParametroGeneral getParametroByDocument(String document) throws MinSaludBusinessException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConfiguracionSingleton.class);
        ParametroGeneral parametroGeneral = context.getBean(ConfiguracionSingleton.class).getParametroPorDocumento(document);

        return parametroGeneral;
    }

    public List<ParametroGeneral> getParametros() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfiguracionSingleton.class);
        this.parametros = context.getBean(ConfiguracionSingleton.class).getParametros();

        return parametros;
    }

    public RespuestaSATARL writeLogSATARL(String empre_form, BigDecimal srv_id, BigDecimal srv_consec, BigDecimal estado_min, String error,  String authorization, String... description){
        Pattern patron = Pattern.compile("\"mensaje\":\"(.+?)\",\"codigo\":\"(.+?)\"");
        Matcher match = patron.matcher(error);
        RespuestaSATARL respuestaSATARL = new RespuestaSATARL();
        respuestaSATARL.setEmpreForm(empre_form);
        respuestaSATARL.setSrvId(srv_id);
        respuestaSATARL.setSrvConsec(srv_consec);
        respuestaSATARL.setTokenMin(authorization);
        respuestaSATARL.setFecRespuesta(LocalDateTime.now());
        respuestaSATARL.setEstadoMin(estado_min);
        if(match.find()) {
            respuestaSATARL.setDescerrorMin(match.group(1));
            respuestaSATARL.setIderrorMin(match.group(2));
        } else {
            respuestaSATARL.setIderrorMin(error);
            assert description != null;
            respuestaSATARL.setDescerrorMin(description == null | description.length == 0? error : description[0]);
        }
        
        return respuestaSATARL;
    }

    private <T> Object sendRequestList(HttpUriRequest request, Class<T> type) throws IllegalAccessException, NoSuchFieldException, IOException {
        return this.sendGeneralRequest(request, type, true, false);
    }

    private <T> Object sendRequest(HttpUriRequest request, Class<T> type) throws IllegalAccessException, NoSuchFieldException, IOException {
        return this.sendGeneralRequest(request, type, false, false);
    }

    private <T> Object sendRequestWithMappingError(HttpUriRequest request, Class<T> type) throws IllegalAccessException, NoSuchFieldException, IOException {
        return this.sendGeneralRequest(request, type, false, true);
    }

    private <T> Object sendGeneralRequest(HttpUriRequest request, Class<T> type, boolean isArray, boolean isPossibleMappingError) throws IOException, NoSuchFieldException, IllegalAccessException {
        ObjectMapper mapper = new ObjectMapper();
        Object returning;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request))
        {
            int status_code = response.getStatusLine().getStatusCode();
            log.info("STATUS CODE".concat(status_code + ""));
            String json_string = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("JSON RESPONSE".concat(json_string + ""));
            Field statusCodeField;
            if (status_code != 200 && status_code != 204 || json_string.equalsIgnoreCase("{}") || json_string.isEmpty())
            {
                if(isPossibleMappingError)
                {
                    returning = mapper.readValue(json_string, type);
                }else {
                    returning = new ErrorDTO();
                    ((ErrorDTO) returning).setStatus_code(status_code);
                    ((ErrorDTO) returning).setError(response.getStatusLine().toString());
                    ((ErrorDTO) returning).setCode_error(response.getStatusLine().getReasonPhrase());
                    ((ErrorDTO) returning).setError_description(json_string);
                }
            }else {
                if(isArray) {
                    returning = mapper.readValue(json_string, mapper.getTypeFactory().constructCollectionType(List.class, type));
                }else {
                    if(type.equals(String.class))
                    {
                        return json_string;
                    }
                    try
                    {
                        log.info("JSON ANTES".concat(json_string));
                        returning = mapper.readValue(json_string, ResponseMinSaludDTO.class);
                        log.info("FULL DTO".concat(returning.toString()));
                        if(((ResponseMinSaludDTO)returning).getCodigo() == null ||((ResponseMinSaludDTO)returning).getCodigo().equalsIgnoreCase("null")) {
                            returning = mapper.readValue(json_string, type);
                        }
                    }catch(Exception ex)
                    {
                        returning = mapper.readValue(json_string, type);
                    }
                    log.info("JSON DESPUES".concat(returning.toString()));

                    try {
                        statusCodeField = returning.getClass().getDeclaredField(SisafitraConstant.STATUS_CODE);
                    }catch(NoSuchFieldException ex)
                    {
                        statusCodeField = returning.getClass().getSuperclass().getDeclaredField(SisafitraConstant.STATUS_CODE);
                    }

                    if(statusCodeField != null)
                    {
                        statusCodeField.setAccessible(true);
                        statusCodeField.set(returning, response.getStatusLine().getStatusCode());
                    }

                }
            }
        }

        return returning;
    }

}