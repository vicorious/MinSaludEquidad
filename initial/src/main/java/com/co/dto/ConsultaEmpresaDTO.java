package com.co.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"numero_documento_empleador", "tipo_documento_empleador", "tipo_reporte",
        "fecha_solicitud", "consecutivo_nit_descentralizado", "fecha_fin_afiliacion"})
public class ConsultaEmpresaDTO
{
    private String tipoDocumentoEmpleador;

    private String numeroDocumentoEmpleador;

    private String consecutivoNitDescentralizado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime fechaSolicitud;

    private String fechaFinAfiliacion;

    private String tipoReporte;

    public ConsultaEmpresaDTO() {
    }

    public String getTipoDocumentoEmpleador() {
        return tipoDocumentoEmpleador;
    }

    @JsonProperty("TipoDocumentoEmpleador")
    public void setTipoDocumentoEmpleador(String tipoDocumentoEmpleador) {
        this.tipoDocumentoEmpleador = tipoDocumentoEmpleador;
    }

    public String getNumeroDocumentoEmpleador() {
        return numeroDocumentoEmpleador;
    }

    @JsonProperty("NumeroDocumentoEmpleador")
    public void setNumeroDocumentoEmpleador(String numeroDocumentoEmpleador) {
        this.numeroDocumentoEmpleador = numeroDocumentoEmpleador;
    }

    public String getConsecutivoNitDescentralizado() {
        return consecutivoNitDescentralizado;
    }

    @JsonProperty("ConsecutivoNitDescentralizado")
    public void setConsecutivoNitDescentralizado(String consecutivoNitDescentralizado) {
        this.consecutivoNitDescentralizado = consecutivoNitDescentralizado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    @JsonProperty("FechaSolicitud")
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaFinAfiliacion() {
        return fechaFinAfiliacion;
    }

    @JsonProperty("FechaFinAfiliacion")
    public void setFechaFinAfiliacion(String fechaFinAfiliacion) {
        this.fechaFinAfiliacion = fechaFinAfiliacion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    @JsonProperty("TipoReporte")
    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
}
