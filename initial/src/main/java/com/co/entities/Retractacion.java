package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({"CodigoArl", "TipoDocumentoEmpleador", "NumeroDocumentoEmpleador",
        "ConsecutivoNITEmpleador", "FechaNovedad"})
@JsonIgnoreProperties(value = { "retractacionId",
        "empre_form", "tokenMin", "fechaCaptura", "fechaReporte",
        "fechaRespuesta", "estadoMin", "naturalezaJuridica", "tipoAportante", "tipoRetractacion" })
@Entity
@Table(name = "SRV_RETRACTACION_EMPRESA")
public class Retractacion extends BaseEntity
{
    @Id
    @Column(name = "SRV_RETRACTACION_EMPRESA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal retractacionId;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "CONSEC_DESENT")
    private String consecDesent;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "TIPO_RETRACTACION")
    private String tipoRetractacion;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "FECRETRACTACION")
    private String fechaRetractacion;

    public BigDecimal getRetractacionId() {
        return retractacionId;
    }

    public void setRetractacionId(BigDecimal retractacionId) {
        this.retractacionId = retractacionId;
    }

    public String getConsecDesent() {
        return consecDesent;
    }

    @JsonProperty("ConsecutivoNITEmpleador")
    public void setConsecDesent(String consecDesent) {
        this.consecDesent = consecDesent;
    }

    public String getTipoRetractacion() {
        return tipoRetractacion;
    }

    public void setTipoRetractacion(String tipoRetractacion) {
        this.tipoRetractacion = tipoRetractacion;
    }

    public String getFechaRetractacion() {
        return fechaRetractacion;
    }

    @JsonProperty("FechaNovedad")
    public void setFechaRetractacion(String fechaRetractacion) {
        this.fechaRetractacion = fechaRetractacion;
    }
}
