package com.co.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.co.annotation.ServiceConfig;
import com.co.builder.PropertiesBuilder;
import com.co.dto.*;
import com.co.entities.*;
import com.co.service.*;
import com.co.controller.BaseController;
import com.co.enums.EstadosEnum;
import com.co.exception.MinSaludBusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.co.utils.SisafitraConstant;

@Component
@RestController
@RequestMapping("/")
public class Controller extends BaseController
{
    @Autowired
	private AfiliacionService afiliacionService;

    @Autowired
	private LogService logService;

    @Autowired
    private ConsultaEmpresaService consultaEmpresaService;

    @Autowired
    private EstructuraEmpresaService estructuraEmpresaService;

    @Autowired
    private InicioLaboralService inicioLaboralService;

    @Autowired
    private TerminacionLaboralService terminacionLaboralService;

    @Autowired
    private ReclasificacionCentroTrabajoService reclasificacionCentroTrabajoService;

    @Autowired
    private RetiroDefinitivoService retiroDefinitivoService;

    @Autowired
    private RetractionService retractionService;

    @Autowired
    private TransladoEmpresaService transladoEmpresaService;

    @Autowired
    private ParametroGeneralService parametroGeneralService;

	@Autowired
	private NovedadesSedesService novedadesSedesService;

	@Autowired
	private NovedadesTransitoriasService novedadesTransitoriasService;

	@Autowired
	private NovedadesCentroTrabajoService novedadesCentroTrabajoService;

	@Autowired
	private IBService ibcService;

	/**
	 *
	 */
	public Controller()
	{
		this.afiliacionService = new AfiliacionService();
		this.logService = new LogService();
		this.consultaEmpresaService = new ConsultaEmpresaService();
		this.estructuraEmpresaService = new EstructuraEmpresaService();
		this.inicioLaboralService = new InicioLaboralService();
		this.terminacionLaboralService = new TerminacionLaboralService();
		this.reclasificacionCentroTrabajoService = new ReclasificacionCentroTrabajoService();
		this.retiroDefinitivoService = new RetiroDefinitivoService();
		this.retractionService = new RetractionService();
		this.transladoEmpresaService = new TransladoEmpresaService();
		this.parametroGeneralService = new ParametroGeneralService();
		this.novedadesSedesService = new NovedadesSedesService();
		this.novedadesTransitoriasService = new NovedadesTransitoriasService();
		this.novedadesCentroTrabajoService = new NovedadesCentroTrabajoService();
		this.ibcService = new IBService();

	}

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@ApiOperation(value = "Retorna el Token", response = TokenDTO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", defaultValue = "830008686", value = "Username en MinSalud", paramType = "form", dataType = "int"),
			@ApiImplicitParam(name = "password", defaultValue = "830008686", value = "Password del username en MinSalud",  paramType = "form", dataType = "int"),
			@ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "Tipo de autenticacion",  paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "client_id", defaultValue = "9160f6412fad4b7fbc5f86d37a8dd680", value = "ClientId para el servicio en MinSalud",  paramType = "form", dataType = "string")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = TokenDTO.class),
			@ApiResponse(code = 201, message = "Created", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 401, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
			@ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
	})
	@PostMapping(path = "/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	/*@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "Token", clientId = "9160f6412fad4b7fbc5f86d37a8dd680",
			uri = "/token", headers = {"Content-Type=application/x-www-form-urlencoded"},
			params = {"username=830008686", "password=830008686", "grant_type=password",
					"client_id=9160f6412fad4b7fbc5f86d37a8dd680"},
			method = RequestMethod.POST)*/
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "Token", clientId = "9160f6412fad4b7fbc5f86d37a8dd680",
			uri = "/token", headers = {"Content-Type=application/x-www-form-urlencoded"},
			params = {"username=830008686", "password=830008686", "grant_type=password",
					"client_id=9160f6412fad4b7fbc5f86d37a8dd680"},
			method = RequestMethod.POST)
	public Object token()
	{
		Object response = null;
		log.info("Token init");
		try
		{
			Method method = new Object() {}.getClass().getEnclosingMethod();
			RequestFormPostDTO request_body = PropertiesBuilder.getAnnotationFeatures(method.getName(), this.getClass());
			log.info("Token request: ".concat(request_body.toString()));
			response =  super.responseFromPostFormRequest(request_body, TokenDTO.class);
			log.info("Token response: ".concat(response.toString()));
		}catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
		}catch (IllegalAccessException | NoSuchFieldException e)
		{
			log.error("Response es invalido para el objeto TokenDTO: ERROR: ".concat(e.getMessage()));
		} catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
		}

		return response;

	}
	@ApiOperation(value = "Afilia todas las empresas de Equidad a MinSalud", response = ResponseContentExitFailDTO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
			@ApiResponse(code = 201, message = "Created", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 401, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
			@ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
	})
	@PostMapping(path = "/AfiliacionARL", consumes = "application/json", produces = "application/json")
	/*@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "AfiliacionARL", clientId = "f45d4049f9a44f839e692f2ca331ec77",
			uri = "/AfiliacionARL", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)*/
	@ServiceConfig(protocol = "https", domain = "miseguridadsocial.gov.co", port = "8062",
			name = "AfiliacionARL", clientId = "1cb2d1aeafa94a7b84f7b83cda27d971",
			uri = "/AfiliacionARL", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object afiliacionARL(@RequestHeader("Authorization") String authorization)
	{
		log.info("AfiliacionARL init with authorization ".concat(authorization));
		List<String> afiliacionesCorrectas = new ArrayList<>();
		List<String> afiliacionesInCorrectas = new ArrayList<>();
		try
		{
			log.info("Consultamos afiliaciones en estado EN_TRAMITE o FALLIDO ");
			List<AfiliacionEmpresa> afiliaciones = this.afiliacionService.afiliacionPorEstado(
					EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName());
			log.info("Numero afiliaciones a registrar: ".concat(String.valueOf(afiliaciones.size())));
            Method method = new Object() {}.getClass().getEnclosingMethod();
			ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(
                    SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
			for(AfiliacionEmpresa afiliacion: afiliaciones)
			{
				log.info("Afiliacion ID: ".concat(afiliacion.getAfiliacionEmpresaId().toPlainString()));
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(
						mapperBody(afiliacion), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION,  authorization);
				log.info("Afiliacion request: ".concat(request_body.toString()));
				Object response = null;
                try
				{
                	response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("Afiliacion response: ".concat(response.toString()));
					if(response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(afiliacion.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), afiliacion.getAfiliacionEmpresaId(),
								EstadosEnum.ERROR.getName(), ((ErrorDTO)response).getError_description(),
								authorization));
						afiliacion.setEstadoMin(EstadosEnum.ERROR.getName());
						afiliacionesInCorrectas.add(afiliacion.getNumeroDocumentoEmpleador().trim());
					} else if(response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(afiliacion.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), afiliacion.getAfiliacionEmpresaId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO)response).getCodigo(),
								authorization));
						afiliacion.setEstadoMin(EstadosEnum.EXITOSO.getName());
						afiliacionesCorrectas.add(afiliacion.getNumeroDocumentoEmpleador().trim());
					}

                }
                catch (Exception e)
                {
					this.logService.save(writeLogSATARL(afiliacion.getEmpre_form(),
                            new BigDecimal(parametro.getValor().trim()), afiliacion.getAfiliacionEmpresaId(),
                            EstadosEnum.ERROR.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
                                    : "FAIL", authorization));
					afiliacion.setEstadoMin(EstadosEnum.ERROR.getName());
                    log.error("Error interno: ".concat(e.getMessage()));
					afiliacionesInCorrectas.add(afiliacion.getNumeroDocumentoEmpleador().trim());
                }

				afiliacion.setTokenMin(authorization);
				afiliacion.setFechaReporte(LocalDateTime.now());
				afiliacion.setFechaRespuesta(LocalDateTime.now());
				this.afiliacionService.add(afiliacion);
			}

            ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(afiliacionesCorrectas);
			responseContentExitFailDTO.setFail(afiliacionesInCorrectas);

			return responseContentExitFailDTO;

		}catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MinSaludBusinessException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
    }
    @ApiOperation(value = "Inicia la relacion laboral en una ARL",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/InicioRelacionLaboralARL", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "InicioRelacionLaboralARL", clientId = "37cf0135c6c5408eb474a8ac0cdd11f2",
			uri =  "/InicioRelacionLaboralARL", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object inicioRelacionLaboralARL(@RequestHeader("Authorization") String authorization)
    {
        Object response = null;
		try
        {
            log.info("InicioRelacionLaboral init with authorization ".concat(authorization));
            List<String> inicioRelacionCorrectas = new ArrayList<>();
            List<String> inicioRelacionInCorrectas = new ArrayList<>();
            ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPLEADO);
            log.info("Consultamos inicioRelacionLaboral en estado EN_TRAMITE o FALLIDO ");
            for(InicioLaboral inicioLaboral: this.inicioLaboralService.getIniciosLaborales(EstadosEnum.FALLIDO.getName(), EstadosEnum.EN_TRAMITE.getName()))
            {
                    log.info("relacionLaboral ID: ".concat(inicioLaboral.getId().toPlainString()));
					Method method = new Object() {}.getClass().getEnclosingMethod();
					//Para crear request
					RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(inicioLaboral), method.getName(), this.getClass(), method.getParameterTypes());
					request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
                    log.info("Request relacionLaboral ID: ".concat(request_body.toString()));

                    try
					{
                        response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
                        log.info("relacionLaboral response: ".concat(response.toString()));
                        if(response instanceof ErrorDTO)
                        {
                            this.logService.save(writeLogSATARL(inicioLaboral.getEmpre_form(),
                                    new BigDecimal(parametro.getValor().trim()), inicioLaboral.getId(),
                                    EstadosEnum.FALLIDO.getName(), ((ErrorDTO)response).getError_description(),
                                    authorization));
                            inicioLaboral.setEstadoMin(EstadosEnum.FALLIDO.getName());
                            inicioRelacionInCorrectas.add(inicioLaboral.getNumeroDocumentoEmpleador().trim());
                        } else if(response instanceof ResponseMinSaludDTO)
                        {
                            this.logService.save(writeLogSATARL(inicioLaboral.getEmpre_form(),
                                    new BigDecimal(parametro.getValor().trim()), inicioLaboral.getId(),
                                    EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO)response).getCodigo(),
                                    authorization));
                            inicioLaboral.setEstadoMin(EstadosEnum.EXITOSO.getName());
                            inicioRelacionCorrectas.add(inicioLaboral.getNumeroDocumentoEmpleador().trim());
                        }

                    }
                    catch (Exception e)
                    {
                        this.logService.save(writeLogSATARL(inicioLaboral.getEmpre_form(),
                                new BigDecimal(parametro.getValor().trim()), inicioLaboral.getId(),
                                EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
                                        : "FAIL", authorization));
                        inicioLaboral.setEstadoMin(EstadosEnum.FALLIDO.getName());
                        log.error("Error interno: ".concat(e.getMessage()));
                        inicioRelacionInCorrectas.add(inicioLaboral.getNumeroDocumentoEmpleador().trim());
                    }
                inicioLaboral.setTokenMin(authorization);
                inicioLaboral.setFechaReporte(LocalDateTime.now());
                inicioLaboral.setFechaRespuesta(LocalDateTime.now());
                this.inicioLaboralService.add(inicioLaboral);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(inicioRelacionCorrectas);
			responseContentExitFailDTO.setFail(inicioRelacionInCorrectas);

			return responseContentExitFailDTO;

		} catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MinSaludBusinessException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}

    @ApiOperation(value = "Termina la relacion laboral en una ARL",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/TerminacionRelacionLaboralARL", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "TerminacionRelacionLaboralARL", clientId = "b09ba3fd1e1e4f05a05daf36bab5a552",
			uri = "/TerminacionRelacionLaboralARL", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object terminacionRelacionLaboralARL(@RequestHeader("Authorization") String authorization)
	{
		Object response = null;
		try
		{
            log.info("terminacionRelacion init with authorization ".concat(authorization));
            List<String> terminacionRelacionCorrectas = new ArrayList<>();
            List<String> terminacionRelacionInCorrectas = new ArrayList<>();
            ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPLEADO);
            log.info("Consultamos terminacionRelacion en estado EN_TRAMITE o FALLIDO ");
            for(TerminacionLaboral terminacionLaboral: this.terminacionLaboralService.getTerminacionesLaborales(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName()))
			{

				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(terminacionLaboral), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("Request terminacionRelacion: ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("response terminacionRelacion: ".concat(response.toString()));
					if(response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(terminacionLaboral.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), terminacionLaboral.getId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO)response).getError_description(),
								authorization));
						terminacionLaboral.setEstadoMin(EstadosEnum.FALLIDO.getName());
						terminacionRelacionCorrectas.add(terminacionLaboral.getNumeroDocumentoEmpleador().trim());
					} else if(response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(terminacionLaboral.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), terminacionLaboral.getId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO)response).getCodigo(),
								authorization));
						terminacionLaboral.setEstadoMin(EstadosEnum.EXITOSO.getName());
						terminacionRelacionCorrectas.add(terminacionLaboral.getNumeroDocumentoEmpleador().trim());
					}

				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(terminacionLaboral.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), terminacionLaboral.getId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					terminacionLaboral.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					terminacionRelacionInCorrectas.add(terminacionLaboral.getNumeroDocumentoEmpleador().trim());
				}

				terminacionLaboral.setTokenMin(authorization);
				terminacionLaboral.setFechaReporte(LocalDateTime.now());
				terminacionLaboral.setFechaRespuesta(LocalDateTime.now());
				this.terminacionLaboralService.add(terminacionLaboral);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(terminacionRelacionCorrectas);
			responseContentExitFailDTO.setFail(terminacionRelacionInCorrectas);

			return responseContentExitFailDTO;

			} catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MinSaludBusinessException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "Consulta empresas para el codigo de ARL enviado", response = ResponseContentExitFailDTO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string"),

	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
			@ApiResponse(code = 201, message = "Created", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 401, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
			@ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
	})
	@PostMapping(path = "/ConsultaEmpresasTrasladadas", consumes = "application/json", produces = "application/json")
	/*@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "ConsultaEmpresasTrasladadas", clientId = "147171ef46c44b41b77b2aaac10ae39b",
			uri = "/ConsultaEmpresasTrasladadas", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)*/
	@ServiceConfig(protocol = "https", domain = "miseguridadsocial.gov.co", port = "8062",
			name = "ConsultaEmpresasTrasladadas", clientId = "9b2d90782dc9475ab38cb244dd088d30",
			uri = "/ConsultaEmpresasTrasladadas", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object consultaEmpresa(@RequestHeader("Authorization") String authorization, @RequestBody String entity_body)
	{
		Object response = null;
		List<String> empresasConsultadas = new ArrayList<>();
		List<String> empresasIncorrectas = new ArrayList<>();
		try
		{
			log.info("Consulta empresa INIT: ");
			Method method = new Object() {}.getClass().getEnclosingMethod();
			RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(entity_body, method.getName(), this.getClass(), method.getParameterTypes());
			log.info("Consulta empresa request BODY: ".concat(request_body.toString()));
			request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
			response = super.responseFromPostListRequest(request_body, ConsultaEmpresaDTO.class);
			log.info("Consulta empresa response BODY: ".concat(response.toString()));
			if(response instanceof ErrorDTO)
				return response;
			List<ConsultaEmpresa> empresas =  this.consultaEmpresaService.transformConsultaEmpresa((List<ConsultaEmpresaDTO>) response, authorization);
			log.info("Consulta empresa Transform: ".concat(empresas.toString()));
            ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(
                    SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);			for(ConsultaEmpresa consultaEmpresa: empresas) {
				try {
					this.consultaEmpresaService.save(consultaEmpresa);
					log.info("Consulta empresa Save Ok!");
					empresasConsultadas.add(consultaEmpresa.getNumeroDocumentoEmpleador());
					this.logService.save(writeLogSATARL(consultaEmpresa.getEmpreForm(), new BigDecimal(parametro.getValor()), consultaEmpresa.getId(), EstadosEnum.EXITOSO.getName(), "OK", authorization));
				}catch (Exception ex)
				{
					empresasIncorrectas.add(consultaEmpresa.getNumeroDocumentoEmpleador());
					this.logService.save(writeLogSATARL(consultaEmpresa.getEmpreForm(), new BigDecimal(parametro.getValor()), consultaEmpresa.getId(), EstadosEnum.FALLIDO.getName(), "FAIL", authorization));
					log.info("Consulta empresa Save fail! ".concat(ex.getMessage()));
				}
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(empresasConsultadas);
			responseContentExitFailDTO.setFail(empresasIncorrectas);

			return responseContentExitFailDTO;

		} catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IllegalAccessException | NoSuchFieldException e)
		{
			log.error("Response es invalido para el objeto ResponseMinSaludDTO: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InvocationTargetException e) {
			log.error("Error al invocar el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@ApiOperation(value = "Consulta estructura de empresas", response = ResponseContentExitFailDTO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
			@ApiResponse(code = 201, message = "Created", response = void.class),
			@ApiResponse(code = 400, message = "Bad Request", response = String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = String.class),
			@ApiResponse(code = 401, message = "Forbidden", response = String.class),
			@ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
			@ApiResponse(code = 500, message = "Failure", response = String.class),
	})
	@PostMapping(path = "/ConsultaEstructuraEmpresa", consumes = "application/json", produces = "application/json")
	/*@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "ConsultaEstructuraEmpresa", clientId = "d99d20985fde4150b924c8d0177691b6",
			uri = "/ConsultaEstructuraEmpresa", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)*/
	@ServiceConfig(protocol = "https", domain = "miseguridadsocial.gov.co", port = "8062",
			name = "ConsultaEstructuraEmpresa", clientId = "f8af5a3156c94e10b9ba1901053f807c",
			uri = "/ConsultaEstructuraEmpresa", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object consultaEstructuraEmpresa(@RequestHeader("Authorization") String authorization) throws MinSaludBusinessException {
			List<String> documentosFull = new ArrayList<>();
			List<String> estructurasIncorrectas = new ArrayList<>();
			Object response = null;

			LocalDateTime now = LocalDateTime.now();
            log.info("Fecha para buscar estructuras Empresa es: ".concat(now.toString()));

			List<ConsultaEmpresa> empresasAConsultar = this.consultaEmpresaService.consultaEmpresaPorFecha(now.toString(), LocalDate.now().toString());
        ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(
                SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
		for(ConsultaEmpresa consultaEmpresa: empresasAConsultar) {
				try
				{
					log.info("Consulta estructura empresa INIT: Empresa a consultar: ".concat(consultaEmpresa.getNumeroDocumentoEmpleador()));
					Method method = new Object(){}.getClass().getEnclosingMethod();
					RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(consultaEmpresa), method.getName(), this.getClass(), method.getParameterTypes());
					request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
					log.info("Consulta estructura empresa REQUEST: ".concat(request_body.toString()));
					response = super.responseFromPostRequest(request_body, EstructuraEmpresa.class);
					if(response instanceof ErrorDTO)
						throw new MinSaludBusinessException(response.toString());
					log.info("Consulta estructura empresa RESPONSE: ".concat(response.toString()));
					EstructuraEmpresa estructuraEmpresa = this.consultaEmpresaService.mapEstructura((EstructuraEmpresa) response, authorization);
					estructuraEmpresa.setConsultaEmpresa(consultaEmpresa);
					log.info("Consulta Estructura MAP: ".concat(estructuraEmpresa.toString()));
					this.estructuraEmpresaService.save(estructuraEmpresa);
					log.info("Consulta Estructura persistida correctamente ".concat(estructuraEmpresa.getEmpreId()));
					documentosFull.add(consultaEmpresa.getNumeroDocumentoEmpleador());
					this.logService.save(writeLogSATARL(consultaEmpresa.getEmpreForm(), new BigDecimal(parametro.getValor().trim()),  consultaEmpresa.getId(),  EstadosEnum.EXITOSO.getName(), "OK", authorization));
				} catch (NoSuchMethodException e)
				{
					log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
					this.logService.save(writeLogSATARL(consultaEmpresa.getEmpreForm(), new BigDecimal(parametro.getValor().trim()), consultaEmpresa.getId(), EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description() : "FAIL" , authorization));
					estructurasIncorrectas.add(consultaEmpresa.getNumeroDocumentoEmpleador().trim());
				}catch (IllegalAccessException | NoSuchFieldException e)
				{
					log.error("Response es invalido para el objeto ResponseMinSaludDTO: ERROR: ".concat(e.getMessage()));
					this.logService.save(writeLogSATARL(consultaEmpresa.getEmpreForm(), new BigDecimal(parametro.getValor().trim()), consultaEmpresa.getId(), EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description() : "FAIL" , authorization));
					estructurasIncorrectas.add(consultaEmpresa.getNumeroDocumentoEmpleador().trim());
				} catch (IOException e)
				{
					log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
					this.logService.save(writeLogSATARL(consultaEmpresa.getEmpreForm(), new BigDecimal(parametro.getValor().trim()), consultaEmpresa.getId(), EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description() : "FAIL" , authorization));
					estructurasIncorrectas.add(consultaEmpresa.getNumeroDocumentoEmpleador());
				} catch (MinSaludBusinessException e)
				{
					log.error("Error de negocio: ".concat(e.getMessage()));
					this.logService.save(writeLogSATARL(consultaEmpresa.getEmpreForm(), new BigDecimal(parametro.getValor().trim()), consultaEmpresa.getId(), EstadosEnum.FALLIDO.getName(), "FAIL", authorization));
					estructurasIncorrectas.add(consultaEmpresa.getNumeroDocumentoEmpleador().trim());
				}
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(documentosFull);
			responseContentExitFailDTO.setFail(estructurasIncorrectas);

			return responseContentExitFailDTO;
	}

    @ApiOperation(value = "Translada el empleador",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/TrasladoEmpleador", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "TrasladoEmpleador", clientId = "ecf9cfbadbe046f8b33f372dbbca31cd",
			uri = "/TrasladoEmpleador", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object trasladoEmpleador(@RequestHeader("Authorization") String authorization) throws MinSaludBusinessException {

		log.info("trasladoEmpleador init with authorization ".concat(authorization));
		Object response = null;
		List<String> trasladoCorrectas = new ArrayList<>();
		List<String> trasladoInCorrectas = new ArrayList<>();
        ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPLEADO);                SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
        try
		{
			for (TransladoEmpresaArl transladoEmpresaArl : this.transladoEmpresaService.getAll(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName()))
			{
				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(transladoEmpresaArl), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("trasladoEmpleador Request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequestWithPossibleMappingError(request_body, ResponseMinSaludDTO.class);
					log.info("trasladoEmpleador Response ".concat(response.toString()));
					if(response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(transladoEmpresaArl.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), transladoEmpresaArl.getTransladoEmpresId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO)response).getError_description(),
								authorization));
						transladoEmpresaArl.setEstadoMin(EstadosEnum.FALLIDO.getName());
						trasladoCorrectas.add(transladoEmpresaArl.getNumeroDocumentoEmpleador().trim());
					} else if(response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(transladoEmpresaArl.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), transladoEmpresaArl.getTransladoEmpresId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO)response).getCodigo(),
								authorization));
						transladoEmpresaArl.setEstadoMin(EstadosEnum.EXITOSO.getName());
						trasladoCorrectas.add(transladoEmpresaArl.getNumeroDocumentoEmpleador().trim());
					}
				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(transladoEmpresaArl.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), transladoEmpresaArl.getTransladoEmpresId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					transladoEmpresaArl.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					trasladoInCorrectas.add(transladoEmpresaArl.getNumeroDocumentoEmpleador().trim());
				}

				transladoEmpresaArl.setTokenMin(authorization);
				transladoEmpresaArl.setFechaReporte(LocalDateTime.now());
				transladoEmpresaArl.setFechaRespuesta(LocalDateTime.now());
				this.transladoEmpresaService.add(transladoEmpresaArl);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(trasladoCorrectas);
			responseContentExitFailDTO.setFail(trasladoInCorrectas);

			return responseContentExitFailDTO;
		} catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

    @ApiOperation(value = "Retractacion del translado del empleador",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/RetractoTrasladoEmpleador", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "RetractoTrasladoEmpleador", clientId = "13d29ae635514990810dd2c6ec54e6ea",
			uri = "/RetractoTrasladoEmpleador", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object retractoTrasladoEmpleador(@RequestHeader("Authorization") String authorization) throws MinSaludBusinessException {

		log.info("retractoTrasladoEmpleador init with authorization ".concat(authorization));
		Object response = null;
		List<String> retractoCorrectas = new ArrayList<>();
		List<String> retractoInCorrectas = new ArrayList<>();
        ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPLEADO);
        try
		{
			for (Retractacion retractacion : this.retractionService.getAll(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName())) {
				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(retractacion), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("retractoTrasladoEmpleador request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequestWithPossibleMappingError(request_body, ResponseMinSaludDTO.class);
					log.info("retractoTrasladoEmpleador response ".concat(response.toString()));
					if(response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(retractacion.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), retractacion.getRetractacionId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO)response).getError_description(),
								authorization));
						retractacion.setEstadoMin(EstadosEnum.FALLIDO.getName());
						retractoCorrectas.add(retractacion.getNumeroDocumentoEmpleador().trim());
					} else if(response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(retractacion.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), retractacion.getRetractacionId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO)response).getCodigo(),
								authorization));
						retractacion.setEstadoMin(EstadosEnum.EXITOSO.getName());
						retractoCorrectas.add(retractacion.getNumeroDocumentoEmpleador().trim());
					}
				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(retractacion.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), retractacion.getRetractacionId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					retractacion.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					retractoInCorrectas.add(retractacion.getNumeroDocumentoEmpleador().trim());
				}

				retractacion.setTokenMin(authorization);
				retractacion.setFechaReporte(LocalDateTime.now());
				retractacion.setFechaRespuesta(LocalDateTime.now());
				this.retractionService.add(retractacion);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(retractoCorrectas);
			responseContentExitFailDTO.setFail(retractoInCorrectas);

			return responseContentExitFailDTO;
		} catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MinSaludBusinessException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}

    @ApiOperation(value = "Retiro definitivo SGRL",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/RetiroDefinitivoEmpresaSGRL", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "RetiroDefinitivoEmpresaSGRL", clientId = "697a4eff67b24efb8714e512bda5c818",
			uri = "/RetiroDefinitivoEmpresaSGRL", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object retiroDefinitivoEmpresaSGRL(@RequestHeader("Authorization") String authorization) throws MinSaludBusinessException {

		log.info("retiroDefinitivoEmpresaSGRL init with authorization ".concat(authorization));
		Object response = null;
		List<String> retiroCorrectas = new ArrayList<>();
		List<String> retiroInCorrectas = new ArrayList<>();
        ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
        try
		{
			for (RetiroDefinitivoSGRL retiroDefinitivoSGRL : this.retiroDefinitivoService.getAll(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName()))
			{
				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(retiroDefinitivoSGRL), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("retiroDefinitivoEmpresaSGRL request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("retiroDefinitivoEmpresaSGRL response ".concat(response.toString()));
					if (response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(retiroDefinitivoSGRL.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), retiroDefinitivoSGRL.getId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO) response).getError_description(),
								authorization));
						retiroDefinitivoSGRL.setEstadoMin(EstadosEnum.FALLIDO.getName());
						retiroCorrectas.add(retiroDefinitivoSGRL.getNumeroDocumentoEmpleador().trim());
					} else if (response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(retiroDefinitivoSGRL.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), retiroDefinitivoSGRL.getId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO) response).getCodigo(),
								authorization));
						retiroDefinitivoSGRL.setEstadoMin(EstadosEnum.EXITOSO.getName());
						retiroCorrectas.add(retiroDefinitivoSGRL.getNumeroDocumentoEmpleador().trim());
					}

				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(retiroDefinitivoSGRL.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), retiroDefinitivoSGRL.getId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					retiroDefinitivoSGRL.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					retiroInCorrectas.add(retiroDefinitivoSGRL.getNumeroDocumentoEmpleador().trim());
				}

				retiroDefinitivoSGRL.setTokenMin(authorization);
				retiroDefinitivoSGRL.setFechaReporte(LocalDateTime.now());
				retiroDefinitivoSGRL.setFechaRespuesta(LocalDateTime.now());
				this.retiroDefinitivoService.add(retiroDefinitivoSGRL);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(retiroCorrectas);
			responseContentExitFailDTO.setFail(retiroInCorrectas);

			return responseContentExitFailDTO;
		}catch (NoSuchMethodException e)
		{
			log.error("Configuracion @ServiceConfig invalida: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

    @ApiOperation(value = "Novedades sedes",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/NovedadesSedes", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "NovedadesSedes", clientId = "f45d4049f9a44f839e692f2ca331ec77",
			uri = "/NovedadesSedes", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object novedadesSedes(@RequestHeader("Authorization")String authorization)
	{
		log.info("novedadesSedes init with authorization ".concat(authorization));
		Object response = null;
		List<String> novedadesSedesCorrectas = new ArrayList<>();
		List<String> novedadesSedesInCorrectas = new ArrayList<>();
		try
		{
			ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(
					SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);

			for (NovedadesSede novedadesSede : this.novedadesSedesService.getNovedadesSedes(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName()))
			{
				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(novedadesSede), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("novedadesSedes request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("novedadesSedes response ".concat(response.toString()));
					if (response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(novedadesSede.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadesSede.getNovedadesSedeId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO) response).getError_description(),
								authorization));
						novedadesSede.setEstadoMin(EstadosEnum.FALLIDO.getName());
						novedadesSedesCorrectas.add(novedadesSede.getNumeroDocumentoEmpleador().trim());
					} else if (response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(novedadesSede.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadesSede.getNovedadesSedeId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO) response).getCodigo(),
								authorization));
						novedadesSede.setEstadoMin(EstadosEnum.EXITOSO.getName());
						novedadesSedesCorrectas.add(novedadesSede.getNumeroDocumentoEmpleador().trim());
					}
				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(novedadesSede.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), novedadesSede.getNovedadesSedeId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					novedadesSede.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					novedadesSedesInCorrectas.add(novedadesSede.getNumeroDocumentoEmpleador().trim());

				}

				novedadesSede.setTokenMin(authorization);
				novedadesSede.setFechaReporte(LocalDateTime.now());
				novedadesSede.setFechaRespuesta(LocalDateTime.now());
				this.novedadesSedesService.add(novedadesSede);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(novedadesSedesCorrectas);
			responseContentExitFailDTO.setFail(novedadesSedesInCorrectas);

			return responseContentExitFailDTO;

		} catch (NoSuchMethodException | IOException | MinSaludBusinessException e)
		{
			log.error("Error de conexion con el servicio: ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @ApiOperation(value = "Novedades centro de trabajo",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/NovedadesCentroTrabajo", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "NovedadesCentroTrabajo", clientId = "f0dccc17a4b84772848fd5f3efe20f4b",
			uri = "/NovedadesCentroTrabajo", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object novedadesCentroTrabajo(@RequestHeader("Authorization")String authorization)
	{
		log.info("novedadesCentroTrabajo init with authorization ".concat(authorization));
		Object response = null;
		ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
		List<String> novedadesCentroCorrectas = new ArrayList<>();
		List<String> novedadesCentroInCorrectas = new ArrayList<>();
		try
		{
			for (NovedadesCentro novedadesCentro : this.novedadesCentroTrabajoService.getNovedadesSedes(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName())) {
				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(novedadesCentro), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("novedadesCentroTrabajo request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("novedadesCentroTrabajo response ".concat(response.toString()));
					if (response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(novedadesCentro.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadesCentro.getId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO) response).getError_description(),
								authorization));
						novedadesCentro.setEstadoMin(EstadosEnum.FALLIDO.getName());
						novedadesCentroCorrectas.add(novedadesCentro.getNumeroDocumentoEmpleador().trim());
					} else if (response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(novedadesCentro.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadesCentro.getId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO) response).getCodigo(),
								authorization));
						novedadesCentro.setEstadoMin(EstadosEnum.EXITOSO.getName());
						novedadesCentroCorrectas.add(novedadesCentro.getNumeroDocumentoEmpleador().trim());
					}

				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(novedadesCentro.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), novedadesCentro.getId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					novedadesCentro.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					novedadesCentroInCorrectas.add(novedadesCentro.getNumeroDocumentoEmpleador().trim());
				}

				novedadesCentro.setTokenMin(authorization);
				novedadesCentro.setFechaReporte(LocalDateTime.now());
				novedadesCentro.setFechaRespuesta(LocalDateTime.now());
				this.novedadesCentroTrabajoService.add(novedadesCentro);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(novedadesCentroCorrectas);
			responseContentExitFailDTO.setFail(novedadesCentroInCorrectas);

			return responseContentExitFailDTO;

		} catch (NoSuchMethodException | JsonProcessingException | MinSaludBusinessException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @ApiOperation(value = "Reclasificacion de centro de trabajo",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
		@PostMapping(path = "/ReclasificacionCentroTrabajo", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "ReclasificacionCentroTrabajo", clientId = "a1829924eb1642a2adbe48799a905e55",
			uri = "/ReclasificacionCentroTrabajo", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object reclasificacionCentroTrabajo(@RequestHeader("Authorization") String authorization) throws MinSaludBusinessException {
		log.info("reclasificacionCentroTrabajo init with authorization ".concat(authorization));
		Object response = null;
		ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
		List<String> reclasificacionCorrectas = new ArrayList<>();
		List<String> reclasificacionInCorrectas = new ArrayList<>();
		try
		{

			for (ReclasificacionCentroTrabajo reclasificacionCentroTrabajo : this.reclasificacionCentroTrabajoService.getAll())
			{
				Method method = new Object() {
				}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(reclasificacionCentroTrabajo), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("reclasificacionCentroTrabajo request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("reclasificacionCentroTrabajo response ".concat(response.toString()));
					if (response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(reclasificacionCentroTrabajo.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), reclasificacionCentroTrabajo.getId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO) response).getError_description(),
								authorization));
						reclasificacionCentroTrabajo.setEstadoMin(EstadosEnum.FALLIDO.getName());
						reclasificacionCorrectas.add(reclasificacionCentroTrabajo.getNumeroDocumentoEmpleador().trim());
					} else if (response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(reclasificacionCentroTrabajo.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), reclasificacionCentroTrabajo.getId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO) response).getCodigo(),
								authorization));
						reclasificacionCentroTrabajo.setEstadoMin(EstadosEnum.EXITOSO.getName());
						reclasificacionCorrectas.add(reclasificacionCentroTrabajo.getNumeroDocumentoEmpleador().trim());
					}
				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(reclasificacionCentroTrabajo.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), reclasificacionCentroTrabajo.getId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					reclasificacionCentroTrabajo.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					reclasificacionInCorrectas.add(reclasificacionCentroTrabajo.getNumeroDocumentoEmpleador().trim());
				}

				reclasificacionCentroTrabajo.setTokenMin(authorization);
				reclasificacionCentroTrabajo.setFechaReporte(LocalDateTime.now());
				reclasificacionCentroTrabajo.setFechaRespuesta(LocalDateTime.now());
				this.reclasificacionCentroTrabajoService.add(reclasificacionCentroTrabajo);

			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(reclasificacionCorrectas);
			responseContentExitFailDTO.setFail(reclasificacionInCorrectas);

			return responseContentExitFailDTO;

		} catch (NoSuchMethodException | JsonProcessingException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @ApiOperation(value = "Novedades transitorias",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/NovedadesTransitorias", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "NovedadesTransitorias", clientId = "6ff4b98c8c22497b9c1d7d7eb5c94644",
			uri = "/NovedadesTransitorias", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object novedadesTransitorias(@RequestHeader("Authorization")String authorization)
	{
		log.info("novedadesTransitorias init with authorization ".concat(authorization));
		Object response = null;
		ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
		List<String> novedadesTransitoriasCorrectas = new ArrayList<>();
		List<String> novedadesTransitoriasInCorrectas = new ArrayList<>();
		try
		{

			for (NovedadesTransitorias novedadesTransitorias : this.novedadesTransitoriasService.getNovedadesTransitorias(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName()))
			{
				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(novedadesTransitorias), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("novedadesTransitorias request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("novedadesTransitorias response ".concat(response.toString()));
					if (response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(novedadesTransitorias.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadesTransitorias.getId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO) response).getError_description(),
								authorization));
						novedadesTransitorias.setEstadoMin(EstadosEnum.FALLIDO.getName());
						novedadesTransitoriasCorrectas.add(novedadesTransitorias.getNumeroDocumentoEmpleador().trim());
					} else if (response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(novedadesTransitorias.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadesTransitorias.getId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO) response).getCodigo(),
								authorization));
						novedadesTransitorias.setEstadoMin(EstadosEnum.EXITOSO.getName());
						novedadesTransitoriasCorrectas.add(novedadesTransitorias.getNumeroDocumentoEmpleador().trim());
					}

				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(novedadesTransitorias.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), novedadesTransitorias.getId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					novedadesTransitorias.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					novedadesTransitoriasInCorrectas.add(novedadesTransitorias.getNumeroDocumentoEmpleador().trim());
				}

				novedadesTransitorias.setTokenMin(authorization);
				novedadesTransitorias.setFechaReporte(LocalDateTime.now());
				novedadesTransitorias.setFechaRespuesta(LocalDateTime.now());
				this.novedadesTransitoriasService.add(novedadesTransitorias);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(novedadesTransitoriasCorrectas);
			responseContentExitFailDTO.setFail(novedadesTransitoriasInCorrectas);

			return responseContentExitFailDTO;

		} catch (NoSuchMethodException | JsonProcessingException | MinSaludBusinessException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @ApiOperation(value = "Modificacion IBC",  response = ResponseContentExitFailDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header con la autenticacion", paramType = "header", dataType = "string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseContentExitFailDTO.class),
            @ApiResponse(code = 201, message = "Created", response = void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 401, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Failure", response = InternalServerErrorDTO.class),
    })
	@PostMapping(path = "/ModificacionIBC", consumes = "application/json", produces = "application/json")
	@ServiceConfig(protocol = "https", domain = "sisafitra.sispropreprod.gov.co", port = "8062",
			name = "ModificacionIBC", clientId = "83d16bb59dc548cb8a75bc43c8da68c6",
			uri = "/ModificacionIBC", headers = {"Content-Type=application/json"},
			method = RequestMethod.POST)
	public Object modificacionIBC(@RequestHeader("Authorization")String authorization)
	{
		log.info("modificacionIBC init with authorization ".concat(authorization));
		Object response = null;
		ParametroGeneral parametro = this.parametroGeneralService.getParametroGeneralParametroDocumentoDataBase(SisafitraConstant.ParameroGeneralConstant.SATARLSERVICIO, new BigDecimal(1), SisafitraConstant.ParameroGeneralConstant.EMPRESA);
		List<String> IBCsCorrectas = new ArrayList<>();
		List<String> IBCInCorrectas = new ArrayList<>();
		try
		{
			for (NovedadIBCTipsal novedadIBCTipsal : this.ibcService.getIBC(EstadosEnum.EN_TRAMITE.getName(), EstadosEnum.FALLIDO.getName())) {
				Method method = new Object() {}.getClass().getEnclosingMethod();
				RequestBodyDTO request_body = PropertiesBuilder.getAnnotationFeatures(mapperBody(novedadIBCTipsal), method.getName(), this.getClass(), method.getParameterTypes());
				request_body.getHeaders().put(SisafitraConstant.AUTHORIZATION, authorization);
				log.info("modificacionIBC request ".concat(request_body.toString()));
				try
				{
					response = super.responseFromPostRequest(request_body, ResponseMinSaludDTO.class);
					log.info("modificacionIBC response ".concat(response.toString()));
					if (response instanceof ErrorDTO)
					{
						this.logService.save(writeLogSATARL(novedadIBCTipsal.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadIBCTipsal.getId(),
								EstadosEnum.FALLIDO.getName(), ((ErrorDTO) response).getError_description(),
								authorization));
						novedadIBCTipsal.setEstadoMin(EstadosEnum.FALLIDO.getName());
						IBCsCorrectas.add(novedadIBCTipsal.getNumeroDocumentoEmpleador().trim());
					} else if (response instanceof ResponseMinSaludDTO)
					{
						this.logService.save(writeLogSATARL(novedadIBCTipsal.getEmpre_form(),
								new BigDecimal(parametro.getValor().trim()), novedadIBCTipsal.getId(),
								EstadosEnum.EXITOSO.getName(), ((ResponseMinSaludDTO) response).getCodigo(),
								authorization));
						novedadIBCTipsal.setEstadoMin(EstadosEnum.EXITOSO.getName());
						IBCsCorrectas.add(novedadIBCTipsal.getNumeroDocumentoEmpleador().trim());
					}

				} catch (Exception e)
				{
					this.logService.save(writeLogSATARL(novedadIBCTipsal.getEmpre_form(),
							new BigDecimal(parametro.getValor().trim()), novedadIBCTipsal.getId(),
							EstadosEnum.FALLIDO.getName(), response instanceof ErrorDTO ? ((ErrorDTO)response).getError_description()
									: "FAIL", authorization));
					novedadIBCTipsal.setEstadoMin(EstadosEnum.FALLIDO.getName());
					log.error("Error interno: ".concat(e.getMessage()));
					IBCInCorrectas.add(novedadIBCTipsal.getNumeroDocumentoEmpleador().trim());
				}

				novedadIBCTipsal.setTokenMin(authorization);
				novedadIBCTipsal.setFechaReporte(LocalDateTime.now());
				novedadIBCTipsal.setFechaRespuesta(LocalDateTime.now());
				this.ibcService.add(novedadIBCTipsal);
			}

			ResponseContentExitFailDTO responseContentExitFailDTO = new ResponseContentExitFailDTO();
			responseContentExitFailDTO.setExito(IBCsCorrectas);
			responseContentExitFailDTO.setFail(IBCInCorrectas);

			return responseContentExitFailDTO;

		} catch (NoSuchMethodException | JsonProcessingException | MinSaludBusinessException e)
		{
			log.error("Error de negocio. ERROR: ".concat(e.getMessage()));
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
