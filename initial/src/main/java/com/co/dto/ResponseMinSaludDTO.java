package com.co.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseMinSaludDTO extends ResponseDTO
{
    private int resultado;

    @JsonIgnore
    private int status_code;

    private String mensaje;

    private String codigo;

    public int getResultado() {
        return resultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public int getStatus_code() {
        return status_code;
    }

    @Override
    public String toString() {
        return "ResponseMinSaludDTO{" +
                "resultado=" + resultado +
                ", mensaje='" + mensaje + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}