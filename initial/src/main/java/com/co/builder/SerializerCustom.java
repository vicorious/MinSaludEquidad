package com.co.builder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class SerializerCustom extends JsonSerializer<String>
{
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException
    {
        String val = value.trim();
        gen.writeString(eliminaAcentos(val));

    }

    public String eliminaAcentos(String s) throws UnsupportedEncodingException {
        if(s == null || s.trim().length() == 0) {
            return "";
        }
        s= new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        log.info("EliminarAcentos ".concat(s));
        s = s.replace('�', '\001');
        log.info("EliminarAcentos ".concat(s));
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        log.info("EliminarAcentos ".concat(s));
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        log.info("EliminarAcentos ".concat(s));
        s = s.replace('\001', 'Ñ');
        log.info("EliminarAcentos final".concat(s));
        return s;
    }
}
