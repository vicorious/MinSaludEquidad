package com.co.builder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class SerializerCustom extends JsonSerializer<String>
{
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException
    {
        String val = value.trim();
        gen.writeString(eliminaAcentos(val));
    }

    public String eliminaAcentos(String s) {
        if(s == null || s.trim().length() == 0) {
            return "";
        }
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[\u0300-\u0301]", "");
    }
}
