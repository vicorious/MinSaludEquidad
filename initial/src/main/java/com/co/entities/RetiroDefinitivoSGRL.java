package com.co.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "TipoDocumentoEmpresa", "NumeroDocumentoEmpresa", "ConsecutivoNITEmpresa",
        "FechaRetiroEmpresa", "CausalRetiroEmpresa"})
@JsonIgnoreProperties(value = { "id",
        "empre_form", "tokenMin", "fechaCaptura", "fechaReporte",
        "fechaRespuesta", "estadoMin", "naturalezaJuridica", "tipoAportante", "actividadEconomica" })
@Entity
@Table(name = "SRV_RETIRO_DEFINITIVO_SGRL")
public class RetiroDefinitivoSGRL extends BaseEntity
{
    @Id
    @Column(name = "SRV_RETIRO_DEFINITIVO_SGRL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "CONSEC_DESENT")
    private String consecDesent;

    @Column(name = "FECFIN_DEFINITIVO")
    private LocalDateTime fecFinDefinitivo;

    @Column(name = "CAUSA_RETIRO")
    private BigDecimal causaRetiro;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getConsecDesent() {
        return consecDesent;
    }

    @JsonProperty("ConsecutivoNITEmpresa")
    public void setConsecDesent(String consecDesent) {
        this.consecDesent = consecDesent;
    }

    public LocalDateTime getFecFinDefinitivo() {
        return fecFinDefinitivo;
    }

    @JsonProperty("FechaRetiroEmpresa")
    public void setFecFinDefinitivo(LocalDateTime fecFinDefinitivo) {
        this.fecFinDefinitivo = fecFinDefinitivo;
    }

    public BigDecimal getCausaRetiro() {
        return causaRetiro;
    }

    @JsonProperty("CausalRetiroEmpresa")
    public void setCausaRetiro(BigDecimal causaRetiro) {
        this.causaRetiro = causaRetiro;
    }
}
