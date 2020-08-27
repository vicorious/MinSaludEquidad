package com.co.dto;

public class ResponseMinSaludDTO extends ResponseDTO
{
    private int resultado;

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