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
@Table(name = "SRV_REPORTE_MORA")
public class ReporteMora extends BaseEntity
{
    @Id
    @Column(name = "SRV_REPORTE_MORA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "CONSEC_DESENT")
    private String periodo;

    @Column(name = "SEDE_COD")
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
}
