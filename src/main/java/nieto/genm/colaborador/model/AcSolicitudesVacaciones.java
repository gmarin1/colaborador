package nieto.genm.colaborador.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ac_solicitudes_vacaciones")
public class AcSolicitudesVacaciones  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="id_usuario", nullable = false)
	private Integer idUsuario;
	
	@Column(name="id_jefe_autorizador")
	private Integer idJefeAutorizador;
	
	@Column(name="fecha_inicio", nullable = false)
	private LocalDate fechaInicio;
	
	@Column(name="fecha_fin", nullable = false)
	private LocalDate fechaFin;

	@Column(name="dias_solicitados", nullable = false)
	private Integer diasSolicitados;
	
	@Column(name="motivo")
	private String motivo;
	
	@Column(name="estado", nullable = false, length = 20)
	private String estado = "PENDIENTE_JEFE";

	@Column(name="fecha_respuesta_jefe")
	private LocalDateTime fechaRespuestaJefe;

	@Column(name="motivo_rechazo_jefe")
	private String motivoRechazoJefe;
	
	@Column(name="fecha_respuesta_rh")
	private LocalDateTime fechaRespuestaRh;

	@Column(name="motivo_rechazo_rh")
	private String motivoRechazoRh;
	
	@Column(name="createdatetime", nullable = false)
	private LocalDateTime createdatetime  = LocalDateTime.now();

	@Column(name="updatedatetime", nullable = false)
	private LocalDateTime updatedatetime  = LocalDateTime.now();

	//constructor vacio
	
	public AcSolicitudesVacaciones() {
	}
	
	//setters and getters

	public Integer getId() {return id;}

	public Integer getIdUsuario() {return idUsuario;}
	public void setIdUsuario(Integer idUsuario) {this.idUsuario = idUsuario;}

	public Integer getIdJefeAutorizador() {return idJefeAutorizador;}
	public void setIdJefeAutorizador(Integer idJefeAutorizador) {this.idJefeAutorizador = idJefeAutorizador;}

	public LocalDate getFechaInicio() {return fechaInicio;}
	public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}

	public LocalDate getFechaFin() {return fechaFin;}
	public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}

	public Integer getDiasSolicitados() {return diasSolicitados;}
	public void setDiasSolicitados(Integer diasSolicitados) {this.diasSolicitados = diasSolicitados;}

	public String getMotivo() {return motivo;}
	public void setMotivo(String motivo) {this.motivo = motivo;}

	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}

	public LocalDateTime getFechaRespuestaJefe() {return fechaRespuestaJefe;}
	public void setFechaRespuestaJefe(LocalDateTime fechaRespuestaJefe) {this.fechaRespuestaJefe = fechaRespuestaJefe;}

	public String getMotivoRechazoJefe() {return motivoRechazoJefe;}
	public void setMotivoRechazoJefe(String motivoRechazoJefe) {this.motivoRechazoJefe = motivoRechazoJefe;}

	public LocalDateTime getFechaRespuestaRh() {return fechaRespuestaRh;}
	public void setFechaRespuestaRh(LocalDateTime fechaRespuestaRh) {this.fechaRespuestaRh = fechaRespuestaRh;}

	public String getMotivoRechazoRh() {return motivoRechazoRh;}
	public void setMotivoRechazoRh(String motivoRechazoRh) {this.motivoRechazoRh = motivoRechazoRh;}

	public LocalDateTime getCreatedatetime() {return createdatetime;}
	public void setCreatedatetime(LocalDateTime createdatetime) {this.createdatetime = createdatetime;}

	public LocalDateTime getUpdatedatetime() {return updatedatetime;}
	public void setUpdatedatetime(LocalDateTime updatedatetime) {this.updatedatetime = updatedatetime;}
}