package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "CodigoArl", "TipoDocumentoEmpleador", "NumeroDocumentoEmpleador",
        "ConsecutivoNITEmpleador", "FechaNovedad"})
@JsonIgnoreProperties(value = { "transladoEmpresId",
        "empre_form", "tokenMin", "fechaCaptura", "fechaReporte",
        "fechaRespuesta", "estadoMin", "naturalezaJuridica", "tipoAportante" })
@Entity
@Table(name = "SRV_TRASLADO_EMPRESA_ARL")
public class TransladoEmpresaArl extends BaseEntity
{
    @Id
    @Column(name = "SRV_TRASLADO_EMPRESA_ARL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal transladoEmpresId;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "CONSEC_DESENT")
    private String consecDesent;

    @Column(name = "fecsolicitud_tras")
    private LocalDateTime fechaSolicitudTranslado;

    public BigDecimal getTransladoEmpresId() {
        return transladoEmpresId;
    }

    public void setTransladoEmpresId(BigDecimal transladoEmpresId) {
        this.transladoEmpresId = transladoEmpresId;
    }

    public String getConsecDesent() {
        return consecDesent;
    }

    @JsonProperty("ConsecutivoNITEmpleador")
    public void setConsecDesent(String consecDesent) {
        this.consecDesent = consecDesent;
    }

    public LocalDateTime getFechaSolicitudTranslado() {
        return fechaSolicitudTranslado;
    }

    @JsonProperty("FechaNovedad")
    public void setFechaSolicitudTranslado(LocalDateTime fechaSolicitudTranslado) {
        this.fechaSolicitudTranslado = fechaSolicitudTranslado;
    }
}
