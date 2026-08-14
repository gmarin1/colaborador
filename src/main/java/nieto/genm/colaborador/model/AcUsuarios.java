package nieto.genm.colaborador.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import nieto.genm.colaborador.dto.PermisosDto;

@Entity
@Table(name="ac_usuarios")
public class AcUsuarios implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="aplicar_descuentos", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer aplicarDescuentos = 0;

	@Column(name="auditoraden", columnDefinition = "INTEGER DEFAULT 0")
	private Integer auditoraden;

	@Column(name="autoriza_descuentos", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer autorizaDescuentos = 0;

	@Column(name="consulta_descuentos", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer consultaDescuentos = 0;

	@CreationTimestamp
	@Column(name="createdatetime", updatable = false)
	private LocalDateTime createdatetime;
	
	@Column(name="email", nullable = false, length = 150)
	private String email;

	@Column(name="estatus", nullable = false)
	private Integer estatus;

	@Column(name="first_name", nullable = false, length = 100)
	private String firstName;
	
	@Column(name="last_name", nullable = false, length = 100)
	private String lastName;
	
	@Column(name="m_precios", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer mPrecios = 0;
	
	@Column(name="password", nullable = false, length = 100)
	private String password;
	
	@Column(name="permisos_default", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer permisosDefault = 0;
	
	@Column(name="prueba", nullable = false)
	private Integer prueba;
	
	@Column(name="pwresp", nullable = false, length = 12)
	private String pwresp;
	
	@Column(name="solicitar_descuentos", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer solicitarDescuentos = 0;
	
	@Column(name="sso_id", nullable = false, length = 100)
	private String ssoId;
	
	@Column(name="super_usuario", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer superUsuario = 0;
	
	@Column(name="telefono", nullable = false, length = 30)
	private String telefono;
	
	@Column(name="tema", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer tema = 0;
	
	@Column(name="tipo_user", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer tipoUser = 0;
	
	@UpdateTimestamp
	@Column(name="updatedatetime", nullable = false)
	private LocalDateTime updatedatetime;
	
	@Column(name = "resolucion", length = 30)
    private String resolucion = "";
	
	@Column(name = "fecha_vencimiento_token", nullable = false)
    private LocalDate fechaVencimientoToken = LocalDate.of(2025, 6, 1);
	
	@Column(name = "token_pin", nullable = false, length = 4, columnDefinition = "char(4) default '0000'")
    private String tokenPin = "0000";
	
	@Column(name="tipo_acceso", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer tipoAcceso = 0;
	
	@Column(name="corporativo", columnDefinition = "INTEGER DEFAULT 0")
	private Integer corporativo = 0;
	
	@Column(name="shortcuts_json")
	private String shortcutsJson;
	
	@Column(name="id_puesto")
	private Integer idPuesto;
	
	 // Transientes
    @Transient
    private String permisos_roles;
    
    @Transient
    private Set<UserProfile> userProfiles = new HashSet<>();

    @Transient
    private List<Empresa> plantas = new ArrayList<>();

    @Transient
    private int unineg;

    @Transient
    private List<PermisosDto> permisos = new ArrayList<>();
	
	//constructor vacio

	public AcUsuarios() {
	}
	
	//setters and getters

	public Integer getId() {return id;}

	public Integer getAplicarDescuentos() {return aplicarDescuentos;}
	public void setAplicarDescuentos(Integer aplicarDescuentos) {this.aplicarDescuentos = aplicarDescuentos;}

	public Integer getAuditoraden() {return auditoraden;}
	public void setAuditoraden(Integer auditoraden) {this.auditoraden = auditoraden;}
	
	public Integer getAutorizaDescuentos() {return autorizaDescuentos;}
	public void setAutorizaDescuentos(Integer autorizaDescuentos) {this.autorizaDescuentos = autorizaDescuentos;}

	public Integer getConsultaDescuentos() {return consultaDescuentos;}
	public void setConsultaDescuentos(Integer consultaDescuentos) {this.consultaDescuentos = consultaDescuentos;}

	public LocalDateTime getCreatedatetime() {return createdatetime;}
	public void setCreatedatetime(LocalDateTime createdatetime) {this.createdatetime = createdatetime;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public Integer getEstatus() {return estatus;}
	public void setEstatus(Integer estatus) {this.estatus = estatus;}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public Integer getmPrecios() {return mPrecios;}
	public void setmPrecios(Integer mPrecios) {this.mPrecios = mPrecios;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public Integer getPermisosDefault() {return permisosDefault;}
	public void setPermisosDefault(Integer permisosDefault) {this.permisosDefault = permisosDefault;}

	public Integer getPrueba() {return prueba;}
	public void setPrueba(Integer prueba) {this.prueba = prueba;}

	public String getPwresp() {return pwresp;}
	public void setPwresp(String pwresp) {this.pwresp = pwresp;}

	public Integer getSolicitarDescuentos() {return solicitarDescuentos;}
	public void setSolicitarDescuentos(Integer solicitarDescuentos) {this.solicitarDescuentos = solicitarDescuentos;}

	public String getSsoId() {return ssoId;}
	public void setSsoId(String ssoId) {this.ssoId = ssoId;}

	public Integer getSuperUsuario() {return superUsuario;}
	public void setSuperUsuario(Integer superUsuario) {this.superUsuario = superUsuario;}

	public String getTelefono() {return telefono;}
	public void setTelefono(String telefono) {this.telefono = telefono;}

	public Integer getTema() {return tema;}
	public void setTema(Integer tema) {this.tema = tema;}

	public Integer getTipoUser() {return tipoUser;}
	public void setTipoUser(Integer tipoUser) {this.tipoUser = tipoUser;}

	public LocalDateTime getUpdatedatetime() {return updatedatetime;}
	public void setUpdatedatetime(LocalDateTime updatedatetime) {this.updatedatetime = updatedatetime;}

	public String getResolucion() {return resolucion;}
	public void setResolucion(String resolucion) {this.resolucion = resolucion;}

	public LocalDate getFechaVencimientoToken() {return fechaVencimientoToken;}
	public void setFechaVencimientoToken(LocalDate fechaVencimientoToken) {this.fechaVencimientoToken = fechaVencimientoToken;}

	public String getTokenPin() {return tokenPin;}
	public void setTokenPin(String tokenPin) {this.tokenPin = tokenPin;}

	public Integer getTipoAcceso() {return tipoAcceso;}
	public void setTipoAcceso(Integer tipoAcceso) {this.tipoAcceso = tipoAcceso;}

	public Integer getCorporativo() {return corporativo;}
	public void setCorporativo(Integer corporativo) {this.corporativo = corporativo;}

	public String getShortcutsJson() {return shortcutsJson;}
	public void setShortcutsJson(String shortcutsJson) {this.shortcutsJson = shortcutsJson;}

	public Integer getIdPuesto() {return idPuesto;}
	public void setIdPuesto(Integer idPuesto) {this.idPuesto = idPuesto;}
	
	@Override
    public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", corporativo=" + corporativo
                + ", telefono=" + telefono + ", estatus=" + estatus + ", tipo_user=" + tipoUser
                + ", createDateTime=" + createdatetime + ", updateDateTime=" + updatedatetime
                + ", super_usuario=" + superUsuario + ", pwresp=" + pwresp + ", unineg=" + unineg + "]";
    }
}