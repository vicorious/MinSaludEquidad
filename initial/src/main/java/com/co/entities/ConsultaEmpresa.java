package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.*;
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
    @Column(name = "FECSOLICITUD")
    private LocalDate fechaSolicitud;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "FECFINAFILIACION")
    private LocalDate fechaFinAfiliacion;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "TIPOREPORTE")
    public String tipoReporte;

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

    public LocalDate getFechaFinAfiliacion() {
        return fechaFinAfiliacion;
    }

    public void setFechaFinAfiliacion(LocalDate fechaFinAfiliacion) {
        this.fechaFinAfiliacion = fechaFinAfiliacion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    @JsonProperty("TipoReporte")
    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    @Override
    public String toString() {
        return "" + '\'' + ", TipoReporte='" + tipoReporte + '\'' + '}';
    }
}
