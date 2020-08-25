package com.co.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.math.BigDecimal;

@JsonPropertyOrder({"CodigoArl", "TipoDocumentoEmpleador", "NumeroDocumentoEmpleador",
        "ConsecutivoNITEmpleador", "Periodo", "TipoReporte"})
@JsonIgnoreProperties(value = { "retractacionId",
        "empre_form", "tokenMin", "fechaCaptura", "fechaReporte",
        "fechaRespuesta", "estadoMin", "naturalezaJuridica", "tipoAportante", "tipoRetractacion" })
@Entity
@Table(name = "SRV_MORA_APORTES_SGRL")
public class ReporteMora extends BaseEntity
{
    @Id
    @Column(name = "SRV_MORA_APORTES_SGRL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "CONSEC_DESENT")
    private String consec;

    @Column(name = "PERIODO")
    private String periodo;

    @Column(name = "TIPO_REPORTE")
    private String tipoRporte;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    @JsonProperty("Periodo")
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTipoRporte() {
        return tipoRporte;
    }

    @JsonProperty("TipoReporte")
    public void setTipoRporte(String tipoRporte) {
        this.tipoRporte = tipoRporte;
    }

    public String getConsec() {
        return consec;
    }
    @JsonProperty("ConsecutivoNITEmpleador")
    public void setConsec(String consec) {
        this.consec = consec;
    }
}
