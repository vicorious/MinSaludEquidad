package com.co.entities;

import com.co.builder.SerializerCustom;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "id",
        "empre_form", "tokenMin", "fechaCaptura", "fechaReporte",
        "fechaRespuesta", "centro", "estadoMin", "naturalezaJuridica", "tipoAportante", "actividadEconomica" })
@Entity
@Table(name = "SRV_ESTRUCTURA_EMPLEADO")
public class Empleado
{
    @Id
    @Column(name = "SRV_ESTRUCTURA_EMPLEADO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "TOKEN_MIN")
    private String tokenMin;

    @Column(name = "FECCAPTURA")
    private LocalDateTime fecCaptura;

    @Column(name = "FECRESPUESTA")
    private LocalDateTime fecRespuesta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SRV_ESTRUCTURA_CENTRO_ID")
    private CentroTrabajo centro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "FECINI_NOV")
    private LocalDate fechaInicio;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_TIPDOC")
    private String tipoDocEmpleado;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_ID")
    private String numeroEmpleado;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_PAPELLIDO")
    private String primerApellidoEmpleado;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_SAPELLIDO")
    private String segundoApellidoEmpleado;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_PNOMBRE")
    private String primerNombreEmpleado;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_SNOMBRE")
    private String segundoNombreEmpleado;

    @Column(name = "TIPO_COTIZANTE")
    private BigDecimal tipoCotizante;

    @Column(name = "SUBTIPO_COTIZANTE")
    private BigDecimal subTipoCotizante;

    @Column(name = "IBC")
    private BigDecimal ibc;
    
    @Column(name = "TIPO_SALARIO")
    private BigDecimal tipoSalario;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE__FECNAC")
    private String empleFecNac;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE__GENERO")
    private String empleGenero;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_EPS")
    private String empleEps;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "EMPLE_AFP")
    private String empleAfp;

    @JsonSerialize(using = SerializerCustom.class)
    @Column(name = "FECFIN_NOV")
    private String fechaFin;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTokenMin() {
        return tokenMin;
    }

    public void setTokenMin(String tokenMin) {
        this.tokenMin = tokenMin;
    }

    public LocalDateTime getFecCaptura() {
        return fecCaptura;
    }

    public void setFecCaptura(LocalDateTime fecCaptura) {
        this.fecCaptura = fecCaptura;
    }

    public LocalDateTime getFecRespuesta() {
        return fecRespuesta;
    }

    public void setFecRespuesta(LocalDateTime fecRespuesta) {
        this.fecRespuesta = fecRespuesta;
    }

    public CentroTrabajo getCentro() {
        return centro;
    }

    public void setCentro(CentroTrabajo centro) {
        this.centro = centro;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    @JsonProperty("fechaInicio")
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getTipoDocEmpleado() {
        return tipoDocEmpleado;
    }

    @JsonProperty("tipoDocumentoIdentificacion")
    public void setTipoDocEmpleado(String tipoDocEmpleado) {
        this.tipoDocEmpleado = tipoDocEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    @JsonProperty("numeroDocumentoIdentificacion")
    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getPrimerApellidoEmpleado() {
        return primerApellidoEmpleado;
    }

    @JsonProperty("primerApellidoTrabajador")
    public void setPrimerApellidoEmpleado(String primerApellidoEmpleado) {
        this.primerApellidoEmpleado = primerApellidoEmpleado;
    }

    public String getSegundoApellidoEmpleado() {
        return segundoApellidoEmpleado;
    }

    @JsonProperty("segundoApellidoTrabajador")
    public void setSegundoApellidoEmpleado(String segundoApellidoEmpleado) {
        this.segundoApellidoEmpleado = segundoApellidoEmpleado;
    }

    public String getPrimerNombreEmpleado() {
        return primerNombreEmpleado;
    }

    @JsonProperty("primerNombreTrabajador")
    public void setPrimerNombreEmpleado(String primerNombreEmpleado) {
        this.primerNombreEmpleado = primerNombreEmpleado;
    }

    public String getSegundoNombreEmpleado() {
        return segundoNombreEmpleado;
    }

    @JsonProperty("segundoNombreTrabajador")
    public void setSegundoNombreEmpleado(String segundoNombreEmpleado) {
        this.segundoNombreEmpleado = segundoNombreEmpleado;
    }

    public BigDecimal getTipoCotizante() {
        return tipoCotizante;
    }

    @JsonProperty("tipoCotizante")
    public void setTipoCotizante(BigDecimal tipoCotizante) {
        this.tipoCotizante = tipoCotizante;
    }

    public BigDecimal getSubTipoCotizante() {
        return subTipoCotizante;
    }

    @JsonProperty("subtipoCotizante")
    public void setSubTipoCotizante(BigDecimal subTipoCotizante) {
        this.subTipoCotizante = subTipoCotizante;
    }

    public BigDecimal getIbc() {
        return ibc;
    }
    @JsonProperty("ibc")
    public void setIbc(BigDecimal  ibc) {
        this.ibc = ibc;
    }

    public String getTipoSalario() {
        return tipoSalario;
    }

    @JsonProperty("tipoSalario")
    public void setTipoSalario(String tipoSalario) {
        this.tipoSalario = tipoSalario;
    }

    public String getEmpleFecNac() {
        return empleFecNac;
    }

    @JsonProperty("fechaNacimiento")
    public void setEmpleFecNac(String empleFecNac) {
        this.empleFecNac = empleFecNac;
    }

    public String getEmpleGenero() {
        return empleGenero;
    }

    @JsonProperty("sexoTrabajador")
    public void setEmpleGenero(String empleGenero) {
        this.empleGenero = empleGenero;
    }

    public String getEmpleEps() {
        return empleEps;
    }

    @JsonProperty("epsTrabajador")
    public void setEmpleEps(String empleEps) {
        this.empleEps = empleEps;
    }

    public String getEmpleAfp() {
        return empleAfp;
    }

    @JsonProperty("afpTrabajador")
    public void setEmpleAfp(String empleAfp) {
        this.empleAfp = empleAfp;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    @JsonProperty("fechafin")
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
