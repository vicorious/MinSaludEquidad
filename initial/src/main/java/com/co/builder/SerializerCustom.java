package com.co.builder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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

    public  String eliminaAcentos(String s) throws UnsupportedEncodingException {
        return new String(s.getBytes(StandardCharsets.ISO_8859_1), charset(s, new String[] { "ISO-8859-1", "UTF-8" })).replace('?', 'Ñ');
    }

    public  String convert(String value, String fromEncoding, String toEncoding) throws UnsupportedEncodingException {
        return new String(value.getBytes(fromEncoding), toEncoding);
    }

    public  String charset(String value, String[] charsets) throws UnsupportedEncodingException {
        String probe = StandardCharsets.UTF_8.name();
        for(String c : charsets) {
            Charset charset = Charset.forName(c);
            if(value.equals(convert(convert(value, charset.name(), probe), probe, charset.name()))) {
                return c;
            }
        }
        return StandardCharsets.UTF_8.name();
    }
}
