package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "CodigoARL", "tipoDocumentoEmpleador", "numeroDocumentoEmpleador",
        "consecutivoNITEmpleador"})
@JsonIgnoreProperties(value = { "id",
        "empreForm", "tokenMinIni", "fecCapturaTokenIni", "fecRespuestaTokenIni",
        "empre_form",  "tokenMin", "fechaCaptura", "fechaReporte", "fechaRespuesta", "estadoMin",
        "tipoReporte", "tokenMinFin", "fecCapturaTokenFin", "fecRespuestaTokenFin", "tipoReporteTokenFin", "tipoReporteMinTokenIni",
        "fechaSolicitud", "fecIniCobertura", "fechaFinAfiliacion", "afiliacionEmpresaId" , "tipoReporteMinTokenFin"})
@Entity
@Table(name = "SRV_CONSULTA_EMPRESA")
public class ControlEstructuraEmpresa
{
    @Column(name = "TOKEN_MIN")
    private String tokenMin;

    @Column(name = "FECCAPTURA")
    private LocalDateTime fechaCaptura;

    @Column(name = "FECREPORTE")
    private LocalDateTime  fechaReporte;

    @Column(name = "FECRESPUESTA")
    private LocalDateTime  fechaRespuesta;

    @Column(name = "ESTADO_MIN")
    private BigDecimal estadoMin;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "COD_ARL")
    private String codArl;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_TIPDOC")
    private String TipoDocumentoEmpleador;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_ID")
    private String NumeroDocumentoEmpleador;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_NIT_DESCEN")
    private String empreNit;

    public String getTipoDocumentoEmpleador() {
        return TipoDocumentoEmpleador  == null ? "null" : TipoDocumentoEmpleador;
    }

    @JsonProperty("TipoDocumentoEmpleador")
    public void setTipoDocumentoEmpleador(String tipoDocumentoEmpleador) {
        TipoDocumentoEmpleador = tipoDocumentoEmpleador;
    }

    public String getNumeroDocumentoEmpleador() {
        return NumeroDocumentoEmpleador  == null ? "null" : NumeroDocumentoEmpleador;
    }

    @JsonProperty("NumeroDocumentoEmpleador")
    public void setNumeroDocumentoEmpleador(String numeroDocumentoEmpleador) {
        NumeroDocumentoEmpleador = numeroDocumentoEmpleador;
    }

    public String getCodArl() {
        return codArl;
    }

    @JsonProperty("CodigoARL")
    public void setCodArl(String codArl) {
        this.codArl = codArl;
    }

    public String getTokenMin() {
        return tokenMin;
    }

    public void setTokenMin(String tokenMin) {
        this.tokenMin = tokenMin;
    }

    public LocalDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(LocalDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public BigDecimal getEstadoMin() {
        return estadoMin;
    }

    public void setEstadoMin(BigDecimal estadoMin) {
        this.estadoMin = estadoMin;
    }

    public String getEmpreNit() {
        return empreNit;
    }

    @JsonProperty("ConsecutivoNITEmpleador")
    public void setEmpreNit(String empreNit) {
        this.empreNit = empreNit;
    }
}
