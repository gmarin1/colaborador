package nieto.genm.colaborador.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ac_colaboradores")
public class AcColaboradores {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="id_usuario", nullable = false)
	private Integer idUsuario;
	
	@Column(name="id_colaborador", length = 30)
	private String idColaborador;
	
	@Column(name="id_alta")
	private Integer idAlta;
	
	@Column(name="traslado_division", length = 100)
	private String trasladoDivision;
	
	@Column(name="id_razon_social")
	private Integer idRazonSocial;
	
	@Column(name="id_planta")
	private Integer idPlanta;
	
	@Column(name="ubicacion", length = 100)
	private String ubicacion;
	
	@Column(name="rfc", length = 13)
	private String rfc;
	
	@Column(name="curp", length = 18)
	private String curp;
	
	@Column(name="nss", length = 11)
	private String nss;
	
	@Column(name="fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name="genero", length = 20)
	private String genero;
	
	@Column(name="estado_civil", length = 20)
	private String estadoCivil;
	
	@Column(name="correo_personal", length = 150)
	private String correoPersonal;
	
	@Column(name="telefono_personal", length = 15)
	private String telefonoPersonal;
	
	@Column(name="telefono_empresa", length = 15)
	private String telefonoEmpresa;
	
	@Column(name="direccion_completa")
	private String direccionCompleta;
	
	@Column(name="contacto_emergencia_nombre", length = 100)
	private String contactoEmergenciaNombre;
	
	@Column(name="contacto_emergencia_parentesco", length = 50)
	private String contactoEmergenciaParentesco;
	
	@Column(name="contacto_emergencia_telefono", length = 15)
	private String contactoEmergenciaTelefono;
	
	@Column(name="fecha_alta")
	private LocalDate fechaAlta;
	
	@Column(name="fecha_baja")
	private LocalDate fechaBaja;
	
	@Column(name="fecha_termino")
	private LocalDate fechaTermino;
	
	@Column(name="tipo_contrato", nullable = false, length = 50)
	private String tipoContrato = "Indeterminado";
	
	@Column(name="sueldo_mensual", nullable = false, precision = 12, scale = 2, columnDefinition = "NUMERIC(12, 2) DEFAULT 0.00")
	private BigDecimal sueldoMensual = BigDecimal.ZERO;
	
	@Column(name="banco_nombre", length = 50)
	private String bancoNombre;
	
	@Column(name="cuenta_bancaria", length = 20)
	private String cuentaBancaria;
	
	@Column(name="clabe_interbancaria", length = 18)
	private String clabeInterbancaria;
	
	@Column(name="tipo_sangre", length = 5)
	private String tipoSangre;
	
	@Column(name="alergias_enfermedades")
	private String alergiasEnfermedades;

	@Column(name="alta", nullable = false, length = 50)
	private String alta = "";

	@Column(name="baja", nullable = false, length = 100)
	private String baja = "";

	@Column(name="cambio", nullable = false, length = 200)
	private String cambio = "";

	@Column(name="createdatetime", nullable = false)
	private LocalDateTime createdatetime = LocalDateTime.now();

	@Column(name="updatedatetime", nullable = false)
	private LocalDateTime updatedatetime = LocalDateTime.now();
	
	@Column(name="sindicalizado", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer sindicalizado = 0;
	
	@Column(name="sindicato")
	private Integer sindicato;
	
	@Column(name="dias_laborales", nullable = false, length = 20)
	private String diasLaborales = "LUNES_VIERNES";
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="idmex")
	private String idmex;
	
	@Column(name="contacto_emergencia_nombre_2", length = 100)
	private String contactoEmergenciaNombre2;
	
	@Column(name="contacto_emergencia_parentesco_2", length = 50)
	private String contactoEmergenciaParentesco2;
	
	@Column(name="contacto_emergencia_telefono_2", length = 15)
	private String contactoEmergenciaTelefono2;
	
	@Column(name="contacto_emergencia_nombre_3", length = 100)
	private String contactoEmergenciaNombre3;
	
	@Column(name="contacto_emergencia_parentesco_3", length = 50)
	private String contactoEmergenciaParentesco3;
	
	@Column(name="contacto_emergencia_telefono_3", length = 15)
	private String contactoEmergenciaTelefono3;

	//constructor vacio
	
	public AcColaboradores() {
	}
	
	//setters and getters

	public Integer getId() {return id;}
	
	public Integer getIdUsuario() {return idUsuario;}
	public void setIdUsuario(Integer idUsuario) {this.idUsuario = idUsuario;}
	
	public String getIdColaborador() {return idColaborador;}
	public void setIdColaborador(String idColaborador) {this.idColaborador = idColaborador;}

	public Integer getIdAlta() {return idAlta;}
	public void setIdAlta(Integer idAlta) {this.idAlta = idAlta;}

	public String getTrasladoDivision() {return trasladoDivision;}
	public void setTrasladoDivision(String trasladoDivision) {this.trasladoDivision = trasladoDivision;}

	public Integer getIdRazonSocial() {return idRazonSocial;}
	public void setIdRazonSocial(Integer idRazonSocial) {this.idRazonSocial = idRazonSocial;}

	public Integer getIdPlanta() {return idPlanta;}
	public void setIdPlanta(Integer idPlanta) {this.idPlanta = idPlanta;}

	public String getUbicacion() {return ubicacion;}
	public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

	public String getRfc() {return rfc;}
	public void setRfc(String rfc) {this.rfc = rfc;}

	public String getCurp() {return curp;}
	public void setCurp(String curp) {this.curp = curp;}

	public String getNss() {return nss;}
	public void setNss(String nss) {this.nss = nss;}

	public LocalDate getFechaNacimiento() {return fechaNacimiento;}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

	public String getGenero() {return genero;}
	public void setGenero(String genero) {this.genero = genero;}

	public String getEstadoCivil() {return estadoCivil;}
	public void setEstadoCivil(String estadoCivil) {this.estadoCivil = estadoCivil;}

	public String getCorreoPersonal() {return correoPersonal;}
	public void setCorreoPersonal(String correoPersonal) {this.correoPersonal = correoPersonal;}

	public String getTelefonoPersonal() {return telefonoPersonal;}
	public void setTelefonoPersonal(String telefonoPersonal) {this.telefonoPersonal = telefonoPersonal;}

	public String getTelefonoEmpresa() {return telefonoEmpresa;}
	public void setTelefonoEmpresa(String telefonoEmpresa) {this.telefonoEmpresa = telefonoEmpresa;}

	public String getDireccionCompleta() {return direccionCompleta;}
	public void setDireccionCompleta(String direccionCompleta) {this.direccionCompleta = direccionCompleta;}

	public String getContactoEmergenciaNombre() {return contactoEmergenciaNombre;}
	public void setContactoEmergenciaNombre(String contactoEmergenciaNombre) {this.contactoEmergenciaNombre = contactoEmergenciaNombre;}

	public String getContactoEmergenciaParentesco() {return contactoEmergenciaParentesco;}
	public void setContactoEmergenciaParentesco(String contactoEmergenciaParentesco) {this.contactoEmergenciaParentesco = contactoEmergenciaParentesco;}

	public String getContactoEmergenciaTelefono() {return contactoEmergenciaTelefono;}
	public void setContactoEmergenciaTelefono(String contactoEmergenciaTelefono) {this.contactoEmergenciaTelefono = contactoEmergenciaTelefono;}

	public LocalDate getFechaAlta() {return fechaAlta;}
	public void setFechaAlta(LocalDate fechaAlta) {this.fechaAlta = fechaAlta;}

	public LocalDate getFechaBaja() {return fechaBaja;}
	public void setFechaBaja(LocalDate fechaBaja) {this.fechaBaja = fechaBaja;}

	public LocalDate getFechaTermino() {return fechaTermino;}
	public void setFechaTermino(LocalDate fechaTermino) {this.fechaTermino = fechaTermino;}

	public String getTipoContrato() {return tipoContrato;}
	public void setTipoContrato(String tipoContrato) {this.tipoContrato = tipoContrato;}

	public BigDecimal getSueldoMensual() {return sueldoMensual;}
	public void setSueldoMensual(BigDecimal sueldoMensual) {this.sueldoMensual = sueldoMensual;}

	public String getBancoNombre() {return bancoNombre;}
	public void setBancoNombre(String bancoNombre) {this.bancoNombre = bancoNombre;}

	public String getCuentaBancaria() {return cuentaBancaria;}
	public void setCuentaBancaria(String cuentaBancaria) {this.cuentaBancaria = cuentaBancaria;}

	public String getClabeInterbancaria() {return clabeInterbancaria;}
	public void setClabeInterbancaria(String clabeInterbancaria) {this.clabeInterbancaria = clabeInterbancaria;}

	public String getTipoSangre() {return tipoSangre;}
	public void setTipoSangre(String tipoSangre) {this.tipoSangre = tipoSangre;}

	public String getAlergiasEnfermedades() {return alergiasEnfermedades;}
	public void setAlergiasEnfermedades(String alergiasEnfermedades) {this.alergiasEnfermedades = alergiasEnfermedades;}

	public String getAlta() {return alta;}
	public void setAlta(String alta) {this.alta = alta;}

	public String getBaja() {return baja;}
	public void setBaja(String baja) {this.baja = baja;}

	public String getCambio() {return cambio;}
	public void setCambio(String cambio) {this.cambio = cambio;}

	public LocalDateTime getCreatedatetime() {return createdatetime;}
	public void setCreatedatetime(LocalDateTime createdatetime) {this.createdatetime = createdatetime;}

	public LocalDateTime getUpdatedatetime() {return updatedatetime;}
	public void setUpdatedatetime(LocalDateTime updatedatetime) {this.updatedatetime = updatedatetime;}

	public Integer getSindicalizado() {return sindicalizado;}
	public void setSindicalizado(Integer sindicalizado) {this.sindicalizado = sindicalizado;}

	public Integer getSindicato() {return sindicato;}
	public void setSindicato(Integer sindicato) {this.sindicato = sindicato;}

	public String getDiasLaborales() {return diasLaborales;}
	public void setDiasLaborales(String diasLaborales) {this.diasLaborales = diasLaborales;}

	public String getFoto() {return foto;}
	public void setFoto(String foto) {this.foto = foto;}

	public String getIdmex() {return idmex;}
	public void setIdmex(String idmex) {this.idmex = idmex;}

	public String getContactoEmergenciaNombre2() {return contactoEmergenciaNombre2;}
	public void setContactoEmergenciaNombre2(String contactoEmergenciaNombre2) {this.contactoEmergenciaNombre2 = contactoEmergenciaNombre2;}

	public String getContactoEmergenciaParentesco2() {return contactoEmergenciaParentesco2;}
	public void setContactoEmergenciaParentesco2(String contactoEmergenciaParentesco2) {this.contactoEmergenciaParentesco2 = contactoEmergenciaParentesco2;}

	public String getContactoEmergenciaTelefono2() {return contactoEmergenciaTelefono2;}
	public void setContactoEmergenciaTelefono2(String contactoEmergenciaTelefono2) {this.contactoEmergenciaTelefono2 = contactoEmergenciaTelefono2;}

	public String getContactoEmergenciaNombre3() {return contactoEmergenciaNombre3;}
	public void setContactoEmergenciaNombre3(String contactoEmergenciaNombre3) {this.contactoEmergenciaNombre3 = contactoEmergenciaNombre3;}

	public String getContactoEmergenciaParentesco3() {return contactoEmergenciaParentesco3;}
	public void setContactoEmergenciaParentesco3(String contactoEmergenciaParentesco3) {this.contactoEmergenciaParentesco3 = contactoEmergenciaParentesco3;}

	public String getContactoEmergenciaTelefono3() {return contactoEmergenciaTelefono3;}
	public void setContactoEmergenciaTelefono3(String contactoEmergenciaTelefono3) {this.contactoEmergenciaTelefono3 = contactoEmergenciaTelefono3;}
}