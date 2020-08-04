package com.co.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "CodigoArl", "TipoDocumentoEmpleador", "NumeroDocumentoEmpleador", "ConsecutivoNITEmpleador",
        "FechaInicioNovedad", "FechaFinNovedad", "TipoNovedad", "TipoDocumentoTrabajador",
        "NumeroDocumentoTrabajador", "PrimerApellidoTrabajador", "PrimerNombreTrabajador", "TipoCotizante", "TipoCotizante",
        "IndicadorNovedad"})
@Entity(name = "SRV_NOVEDADES_TRANSITORIAS")
public class NovedadesTransitorias extends BaseEntity
{
    @Id
    @Column(name = "SRV_NOVEDADES_TRANSITORIAS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "CONSEC_DESENT")
    private String consecDesent;

    @Column(name = "FECINI_NOVTRANSITORIA")
    private String fecIniNovTransitoria;

    @Column(name = "FECFIN_NOVTRANSITORIA")
    private String fecFinNovTransitoria;

    @Column(name = "TIPO_NOVTRANSITORIA")
    private BigDecimal tipoNovTransitoria;

    @Column(name = "EMPLE_TIPDOC")
    private String empleTipDoc;

    @Column(name = "EMPLE_ID")
    private String empleId;

    @Column(name = "EMPLE_PAPELLIDO")
    private String emprePapellido;

    @Column(name = "EMPLE_PNOMBRE")
    private String emprePnombre;

    @Column(name = "TIPO_COTIZANTE")
    private BigDecimal tipoCotizante;

    @Column(name = "SUBTIPO_COTIZANTE")
    private BigDecimal subTipoCotizante;

    @Column(name = "INDICADOR_NOVEDAD")
    private String indicadorNovedad;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getConsecDesent() {
        return consecDesent;
    }

    @JsonProperty("ConsecutivoNITEmpleador")
    public void setConsecDesent(String consecDesent) {
        this.consecDesent = consecDesent;
    }

    public String getFecIniNovTransitoria() {
        return fecIniNovTransitoria;
    }

    @JsonProperty("FechaInicioNovedad")
    public void setFecIniNovTransitoria(String fecIniNovTransitoria) {
        this.fecIniNovTransitoria = fecIniNovTransitoria;
    }

    public String getFecFinNovTransitoria() {
        return fecFinNovTransitoria;
    }

    @JsonProperty("FechaFinNovedad")
    public void setFecFinNovTransitoria(String fecFinNovTransitoria) {
        this.fecFinNovTransitoria = fecFinNovTransitoria;
    }

    public BigDecimal getTipoNovTransitoria() {
        return tipoNovTransitoria;
    }

    @JsonProperty("TipoNovedad")
    public void setTipoNovTransitoria(BigDecimal tipoNovTransitoria) {
        this.tipoNovTransitoria = tipoNovTransitoria;
    }

    public String getEmpleTipDoc() {
        return empleTipDoc;
    }

    @JsonProperty("TipoDocumentoTrabajador")
    public void setEmpleTipDoc(String empleTipDoc) {
        this.empleTipDoc = empleTipDoc;
    }

    public String getEmpleId() {
        return empleId;
    }

    @JsonProperty("NumeroDocumentoTrabajador")
    public void setEmpleId(String empleId) {
        this.empleId = empleId;
    }

    public String getEmprePapellido() {
        return emprePapellido;
    }

    @JsonProperty("PrimerApellidoTrabajador")
    public void setEmprePapellido(String emprePapellido) {
        this.emprePapellido = emprePapellido;
    }

    public String getEmprePnombre() {
        return emprePnombre;
    }

    @JsonProperty("PrimerNombreTrabajador")
    public void setEmprePnombre(String emprePnombre) {
        this.emprePnombre = emprePnombre;
    }

    public BigDecimal getTipoCotizante() {
        return tipoCotizante;
    }

    @JsonProperty("TipoCotizante")
    public void setTipoCotizante(BigDecimal tipoCotizante) {
        this.tipoCotizante = tipoCotizante;
    }

    public BigDecimal getSubTipoCotizante() {
        return subTipoCotizante;
    }

    @JsonProperty("SubtipoCotizante")
    public void setSubTipoCotizante(BigDecimal subTipoCotizante) {
        this.subTipoCotizante = subTipoCotizante;
    }

    public String getIndicadorNovedad() {
        return indicadorNovedad;
    }

    @JsonProperty("IndicadorNovedad")
    public void setIndicadorNovedad(String indicadorNovedad) {
        this.indicadorNovedad = indicadorNovedad;
    }
}
