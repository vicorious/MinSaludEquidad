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

@JsonPropertyOrder({ "CodigoARL", "TipoDocumentoEmpleador", "NumeroDocumentoEmpleador",
        "ConsecutivoNITEmpleador", "CodigoSede", "CodigoCentroTrabajo", "FechaNovedaCambioIBC",
        "TipoDocumentoTrabajador", "NumeroDocumentoTrabajador", "PrimerApellidoTrabajador", "PrimerNombreTrabajador", "TipoCotizante",
        "SubtipoCotizante", "IBC", "TipoSalario"})
@JsonIgnoreProperties(value = { "id",
        "empre_form", "tokenMin", "fechaCaptura", "fechaReporte",
        "fechaRespuesta", "estadoMin", "naturalezaJuridica", "tipoAportante", "actividadEconomica" })
@Entity
@Table(name = "SRV_VARIACION_IBC")
public class NovedadIBCTipsal extends BaseEntity
{
    @Id
    @Column(name = "SRV_VARIACION_IBC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "CONSEC_DESENT")
    private String consecDesent;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "COD_SEDE")
    private String sedeCod;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "COD_CENTRO")
    private String centroCod;

    /*@JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)*/
    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "FECINI_NOV")
    private String fecIniNov;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_TIPDOC")
    private String empleTipDoc;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_ID")
    private String empleId;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_PAPELLIDO")
    private String emprePapellido;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_PNOMBRE")
    private String emprePnombre;

    @Column(name = "TIPO_COTIZANTE")
    private BigDecimal tipoCotizante;

    @Column(name = "SUBTIPO_COTIZANTE")
    private BigDecimal subTipoCotizante;

    @Column(name = "IBC")
    private BigDecimal ibc;

    @Column(name = "TIPO_SALARIO")
    private BigDecimal tipoSalario;

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

    public String getSedeCod() {
        return sedeCod;
    }

    @JsonProperty("CodigoSede")
    public void setSedeCod(String sedeCod) {
        this.sedeCod = sedeCod;
    }

    public String getCentroCod() {
        return centroCod;
    }

    @JsonProperty("CodigoCentroTrabajo")
    public void setCentroCod(String centroCod) {
        this.centroCod = centroCod;
    }

    public String getFecIniNov() {
        return fecIniNov;
    }

    @JsonProperty("FechaNovedaCambioIBC")
    public void setFecIniNov(String fecIniNov) {
        this.fecIniNov = fecIniNov;
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

    public BigDecimal getIbc() {
        return ibc;
    }

    @JsonProperty("IBC")
    public void setIbc(BigDecimal ibc) {
        this.ibc = ibc;
    }

    public BigDecimal getTipoSalario() {
        return tipoSalario;
    }

    @JsonProperty("TipoSalario")
    public void setTipoSalario(BigDecimal tipoSalario) {
        this.tipoSalario = tipoSalario;
    }
}
