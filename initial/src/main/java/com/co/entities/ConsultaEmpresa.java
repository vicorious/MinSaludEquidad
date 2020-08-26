package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonPropertyOrder({  "tipoDocumentoEmpleador", "numeroDocumentoEmpleador",
        "consecutivoNITEmpleador"})
@JsonIgnoreProperties(value = { "id",
        "empreForm", "tokenMinIni", "fecCapturaTokenIni", "fecRespuestaTokenIni",
        "empre_form",  "tokenMin", "fechaCaptura", "fechaReporte", "fechaRespuesta", "estadoMin",
        "tipoReporte", "tokenMinFin", "fecCapturaTokenFin", "fecRespuestaTokenFin", "tipoReporteTokenFin", "tipoReporteMinTokenIni",
        "fechaSolicitud", "fecIniCobertura", "fechaFinAfiliacion", "afiliacionEmpresaId" , "tipoReporteMinTokenFin"})
@Entity
@Table(name = "SRV_CONSULTA_EMPRESA")
public class ConsultaEmpresa
{
    @Id
    @Column(name = "SRV_CONSULTA_EMPRESA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "EMPRE_FORM")
    private String empreForm;

    @Column(name = "TOKEN_MIN")
    private String tokenMinIni;

    @Column(name = "FECCAPTURA_TOKEN")
    private String fecCapturaTokenIni;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_TIPDOC")
    private String tipoDocumentoEmpleador;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_ID")
    private String numeroDocumentoEmpleador;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_NIT_DESCEN")
    private String consecutivoNitDescentralizado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECSOLICITUD")
    private LocalDate fechaSolicitud;

    @Column(name = "FECFINAFILIACION")
    private String fechaFinAfiliacion;

    public ConsultaEmpresa() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal afiliacionEmpresaId) {
        this.id = afiliacionEmpresaId;
    }

    public String getEmpreForm() {
        return empreForm;
    }

    public void setEmpreForm(String empreForm) {
        this.empreForm = empreForm;
    }

    public String getTokenMinIni() {
        return tokenMinIni;
    }

    public void setTokenMinIni(String tokenMinIni) {
        this.tokenMinIni = tokenMinIni;
    }

    public String getFecCapturaTokenIni() {
        return fecCapturaTokenIni;
    }

    public void setFecCapturaTokenIni(String fecCapturaTokenIni) {
        this.fecCapturaTokenIni = fecCapturaTokenIni;
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

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaFinAfiliacion() {
        return fechaFinAfiliacion;
    }

    public void setFechaFinAfiliacion(String fechaFinAfiliacion) {
        this.fechaFinAfiliacion = fechaFinAfiliacion;
    }

    @Override
    public String toString() {
        return "ConsultaEmpresa{" +
                "id=" + id +
                ", empreForm='" + empreForm + '\'' +
                ", tokenMinIni='" + tokenMinIni + '\'' +
                ", fecCapturaTokenIni='" + fecCapturaTokenIni + '\'' +
                ", tipoDocumentoEmpleador='" + tipoDocumentoEmpleador + '\'' +
                ", numeroDocumentoEmpleador='" + numeroDocumentoEmpleador + '\'' +
                ", consecutivoNitDescentralizado='" + consecutivoNitDescentralizado + '\'' +
                ", fechaSolicitud='" + fechaSolicitud.toString() + '\'' +
                ", fechaFinAfiliacion='" + fechaFinAfiliacion.toString() + '\'' +
                '}';
    }
}
