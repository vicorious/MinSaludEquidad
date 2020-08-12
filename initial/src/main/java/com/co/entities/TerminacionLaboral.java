package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "TipoDocumentoEmpleador", "NumeroDocumentoEmpleador", "ConsecutivoNITEmpleador",
        "CodigoSede", "CodigoCentroTrabajo", "FechaFinNovedad", "TipoDocumentoEmpleado",
        "NumeroDocumentoEmpleado", "PrimerNombreEmpleado", "PrimerApellidoEmpleado", "TipoCotizante", "SubTipoCotizante"})
@JsonIgnoreProperties(value = { "id",
        "empreForm", "tokenMin", "fechaCaptura", "fecReporte",
        "fecRespuesta", "estadoMin", "estadoMin", "empre_form",  "tokenMin", "fechaCaptura", "fechaReporte", "fechaRespuesta", "estadoMin" })
@Entity
@Table(name = "SRV_TERMINACION_LABORAL")
public class TerminacionLaboral extends BaseEntity
{
    @Id
    @Column(name = "SRV_TERMINACION_LABORAL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "CONSEC_DESENT")
    private String consecDesent;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_COD")
    private String sedeCod;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "CENTRO_COD")
    private String centroCod;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "FECFIN_LABORAL")
    private String fecFinNovedad;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_TIPDOC")
    private String empleTipDoc;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_ID")
    private String empleId;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_PAPELLIDO")
    private String empleApellido;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_PNOMBRE")
    private String empleNombre;

    @Column(name = "TIPO_COTIZANTE")
    private BigDecimal tipoCotizante;

    @Column(name = "SUBTIPO_COTIZANTE")
    private BigDecimal subTipoCotizante;

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

    public String getFecFinNovedad() {
        return fecFinNovedad;
    }

    @JsonProperty("FechaFinNovedad")
    public void setFecFinNovedad(String fecFinNovedad) {
        this.fecFinNovedad = fecFinNovedad;
    }

    public String getEmpleTipDoc() {
        return empleTipDoc;
    }

    @JsonProperty("TipoDocumentoEmpleado")
    public void setEmpleTipDoc(String empleTipDoc) {
        this.empleTipDoc = empleTipDoc;
    }

    public String getEmpleId() {
        return empleId;
    }

    @JsonProperty("NumeroDocumentoEmpleado")
    public void setEmpleId(String empleId) {
        this.empleId = empleId;
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

    @JsonProperty("SubTipoCotizante")
    public void setSubTipoCotizante(BigDecimal subTipoCotizante) {
        this.subTipoCotizante = subTipoCotizante;
    }

    public String getEmpleApellido() {
        return empleApellido;
    }

    @JsonProperty("PrimerNombreEmpleado")
    public void setEmpleApellido(String empleApellido) {
        this.empleApellido = empleApellido;
    }

    public String getEmpleNombre() {
        return empleNombre;
    }

    @JsonProperty("PrimerApellidoEmpleado")
    public void setEmpleNombre(String empleNombre) {
        this.empleNombre = empleNombre;
    }
}
