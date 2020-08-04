package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "CodigoArl", "TipoDocumentoEmpleador", "NumeroDocumentoEmpleador", "ConsecutivoNITEmpleador",
        "RazonSocialEmpleador", "PrimerNombreEmpleador", "PrimerApellidoEmpleador", "FechaNovedad",
        "CodigoSede", "SedePrincipal", "NombreSede", "MunicipioSede", "DireccionSede",
        "ZonaSede", "TelefonoSede", "CorreoSede", "TipoDocumentoResponsable", "NumeroDocumentoResponsable", "PrimerNombreResponsable",
        "PrimerApellidoResponsable", "IndicadorActualizacion", "SedeEmpleadorMision", "TipoDocumentoEmpleadorMs", "NumeroDocumentoEmpleadorMs", "ConsecutivoNITEmpleadorMs"})
@JsonIgnoreProperties(value = { "id",
        "empreForm", "tokenMin", "fecCaptura", "fecReporte",
        "fecRespuesta", "estadoMin", "estadoMin" })
@Entity(name = "NovedadesSede")
@Table(name = "SRV_ESTRUCTURA_SEDE")
@DynamicUpdate
public class NovedadesSede extends BaseEntity
{
    @Id
    @Column(name = "SRV_ESTRUCTURA_SEDE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal novedadesSedeId;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_NIT_DESCENT")
    private String empreNitDescent;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_RAZSOC")
    private String empreRazsoc;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_PERNAT_PRINOM")
    private String emprePernatPriNom;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPRE_PERNAT_PRIAPE")
    private String emprePernatPriApe;

    @Column(name = "FECNOV_SEDE")
    private LocalDateTime fechaNovedadSede;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "COD_SEDE")
    private String codSede;

    @Column(name = "SEDE_PRINCIPAL")
    private BigDecimal sedePrincipal;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_NOMBRE")
    private String sedeNonbre;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_MUNIC")
    private String sedeMunicipio;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_DIR")
    private String sedeDir;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_ZONA_UBI")
    private String sedeZonaUbi;

    @Column(name = "SEDE_TEL")
    private BigDecimal sedeTel;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_EMAIL")
    private String sedeEmail;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "TIPDOC_RESPO")
    private String tipDocRespo;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_NUMDOC_RESPO")
    private String sedeNumDocRespo;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_RESPO_PRIAPE")
    private String sedeRespoPriApe;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "SEDE_RESPO_PRINOM")
    private String sedeRespoPriNom;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "INDI_ACTU")
    private String indiActu;

    @Column(name = "SEDE_EMPRE_MISION")
    private BigDecimal sedeEmpreMision;

    @Column(name = "TIPDOC_EMPRE_MISION")
    private BigDecimal tipDocEmpreMision;

    @Column(name = "NUMDOC_EMPRE_MISION")
    private BigDecimal numDocEmpreMision;

    @Column(name = "EMPRE_MISION_NIT_DESCEN")
    private BigDecimal empreMisionNitDescen;

    public BigDecimal getNovedadesSedeId() {
        return novedadesSedeId;
    }

    public void setNovedadesSedeId(BigDecimal novedadesSedeId) {
        this.novedadesSedeId = novedadesSedeId;
    }

    public String getEmpreNitDescent() {
        return empreNitDescent;
    }

    @JsonProperty("ConsecutivoNITEmpleador")
    public void setEmpreNitDescent(String empreNitDescent) {
        this.empreNitDescent = empreNitDescent;
    }

    public String getEmpreRazsoc() {
        return empreRazsoc;
    }

    @JsonProperty("RazonSocialEmpleador")
    public void setEmpreRazsoc(String empreRazsoc) {
        this.empreRazsoc = empreRazsoc;
    }

    public String getEmprePernatPriNom() {
        return emprePernatPriNom;
    }

    @JsonProperty("PrimerNombreEmpleador")
    public void setEmprePernatPriNom(String emprePernatPriNom) {
        this.emprePernatPriNom = emprePernatPriNom;
    }

    public String getEmprePernatPriApe() {
        return emprePernatPriApe;
    }

    @JsonProperty("PrimerApellidoEmpleador")
    public void setEmprePernatPriApe(String emprePernatPriApe) {
        this.emprePernatPriApe = emprePernatPriApe;
    }

    public LocalDateTime getFechaNovedadSede() {
        return fechaNovedadSede;
    }

    @JsonProperty("FechaNovedad")
    public void setFechaNovedadSede(LocalDateTime fechaNovedadSede) {
        this.fechaNovedadSede = fechaNovedadSede;
    }

    public String getCodSede() {
        return codSede;
    }

    @JsonProperty("CodigoSede")
    public void setCodSede(String codSede) {
        this.codSede = codSede;
    }

    public BigDecimal getSedePrincipal() {
        return sedePrincipal;
    }

    @JsonProperty("SedePrincipal")
    public void setSedePrincipal(BigDecimal sedePrincipal) {
        this.sedePrincipal = sedePrincipal;
    }

    public String getSedeNonbre() {
        return sedeNonbre;
    }

    @JsonProperty("NombreSede")
    public void setSedeNonbre(String sedeNonbre) {
        this.sedeNonbre = sedeNonbre;
    }

    public String getSedeMunicipio() {
        return sedeMunicipio;
    }

    @JsonProperty("MunicipioSede")
    public void setSedeMunicipio(String sedeMunicipio) {
        this.sedeMunicipio = sedeMunicipio;
    }

    public String getSedeDir() {
        return sedeDir;
    }

    @JsonProperty("DireccionSede")
    public void setSedeDir(String sedeDir) {
        this.sedeDir = sedeDir;
    }

    public String getSedeZonaUbi() {
        return sedeZonaUbi;
    }

    @JsonProperty("ZonaSede")
    public void setSedeZonaUbi(String sedeZonaUbi) {
        this.sedeZonaUbi = sedeZonaUbi;
    }

    public BigDecimal getSedeTel() {
        return sedeTel;
    }

    @JsonProperty("TelefonoSede")
    public void setSedeTel(BigDecimal sedeTel) {
        this.sedeTel = sedeTel;
    }

    public String getSedeEmail() {
        return sedeEmail;
    }

    @JsonProperty("CorreoSede")
    public void setSedeEmail(String sedeEmail) {
        this.sedeEmail = sedeEmail;
    }

    public String getTipDocRespo() {
        return tipDocRespo;
    }

    @JsonProperty("TipoDocumentoResponsable")
    public void setTipDocRespo(String tipDocRespo) {
        this.tipDocRespo = tipDocRespo;
    }

    public String getSedeNumDocRespo() {
        return sedeNumDocRespo;
    }

    @JsonProperty("NumeroDocumentoResponsable")
    public void setSedeNumDocRespo(String sedeNumDocRespo) {
        this.sedeNumDocRespo = sedeNumDocRespo;
    }

    public String getSedeRespoPriApe() {
        return sedeRespoPriApe;
    }

    @JsonProperty("PrimerApellidoResponsable")
    public void setSedeRespoPriApe(String sedeRespoPriApe) {
        this.sedeRespoPriApe = sedeRespoPriApe;
    }

    public String getSedeRespoPriNom() {
        return sedeRespoPriNom;
    }

    @JsonProperty("PrimerNombreResponsable")
    public void setSedeRespoPriNom(String sedeRespoPriNom) {
        this.sedeRespoPriNom = sedeRespoPriNom;
    }

    public String getIndiActu() {
        return indiActu;
    }

    @JsonProperty("IndicadorActualizacion")
    public void setIndiActu(String indiActu) {
        this.indiActu = indiActu;
    }

    public BigDecimal getSedeEmpreMision() {
        return sedeEmpreMision;
    }

    @JsonProperty("SedeEmpleadorMision")
    public void setSedeEmpreMision(BigDecimal sedeEmpreMision) {
        this.sedeEmpreMision = sedeEmpreMision;
    }

    public BigDecimal getTipDocEmpreMision() {
        return tipDocEmpreMision;
    }

    @JsonProperty("TipoDocumentoEmpleadorMs")
    public void setTipDocEmpreMision(BigDecimal tipDocEmpreMision) {
        this.tipDocEmpreMision = tipDocEmpreMision;
    }

    public BigDecimal getNumDocEmpreMision() {
        return numDocEmpreMision;
    }

    @JsonProperty("NumeroDocumentoEmpleadorMs")
    public void setNumDocEmpreMision(BigDecimal numDocEmpreMision) {
        this.numDocEmpreMision = numDocEmpreMision;
    }

    public BigDecimal getEmpreMisionNitDescen() {
        return empreMisionNitDescen;
    }

    @JsonProperty("ConsecutivoNITEmpleadorMs")
    public void setEmpreMisionNitDescen(BigDecimal empreMisionNitDescen) {
        this.empreMisionNitDescen = empreMisionNitDescen;
    }
}
