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
@Table(name="ac_periodos_vacaciones")
public class AcPeriodosVacaciones  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="id_usuario", nullable = false)
	private Integer idUsuario;
	
	@Column(name="anio_periodo", nullable = false)
	private Integer anioPeriodo;
	
	@Column(name="fecha_inicio", nullable = false)
	private LocalDate fechaInicio;
	
	@Column(name="fecha_fin", nullable = false)
	private LocalDate fechaFin;
	
	@Column(name="fecha_caducidad", nullable = false)
	private LocalDate fechaCaducidad;
	
	@Column(name="dias_otorgados", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer diasOtorgados = 0;
	
	@Column(name="dias_tomados", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer diasTomados = 0;
	
	@Column(name="es_carga_inicial", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer esCargaInicial = 0;

	@Column(name="activo", nullable = false, columnDefinition = "INTEGER DEFAULT 1")
	private Integer activo = 1;

	@Column(name="alta", nullable = false, length = 50)
	private String alta = "";

	@Column(name="cambio", nullable = false, length = 200)
	private String cambio = "";

	@Column(name="createdatetime", nullable = false)
	private LocalDateTime createdatetime = LocalDateTime.now();

	@Column(name="updatedatetime", nullable = false)
	private LocalDateTime updatedatetime = LocalDateTime.now();
	
	//constructor vacio

	public AcPeriodosVacaciones() {
	}
	
	//setters and getters

	public Integer getId() {return id;}

	public Integer getIdUsuario() {return idUsuario;}
	public void setIdUsuario(Integer idUsuario) {this.idUsuario = idUsuario;}

	public Integer getAnioPeriodo() {return anioPeriodo;}
	public void setAnioPeriodo(Integer anioPeriodo) {this.anioPeriodo = anioPeriodo;}

	public LocalDate getFechaInicio() {return fechaInicio;}
	public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}

	public LocalDate getFechaFin() {return fechaFin;}
	public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}

	public LocalDate getFechaCaducidad() {return fechaCaducidad;}
	public void setFechaCaducidad(LocalDate fechaCaducidad) {this.fechaCaducidad = fechaCaducidad;}

	public Integer getDiasOtorgados() {return diasOtorgados;}
	public void setDiasOtorgados(Integer diasOtorgados) {this.diasOtorgados = diasOtorgados;}

	public Integer getDiasTomados() {return diasTomados;}
	public void setDiasTomados(Integer diasTomados) {this.diasTomados = diasTomados;}

	public Integer getEsCargaInicial() {return esCargaInicial;}
	public void setEsCargaInicial(Integer esCargaInicial) {this.esCargaInicial = esCargaInicial;}

	public Integer getActivo() {return activo;}
	public void setActivo(Integer activo) {this.activo = activo;}

	public String getAlta() {return alta;}
	public void setAlta(String alta) {this.alta = alta;}

	public String getCambio() {return cambio;}
	public void setCambio(String cambio) {this.cambio = cambio;}

	public LocalDateTime getCreatedatetime() {return createdatetime;}
	public void setCreatedatetime(LocalDateTime createdatetime) {this.createdatetime = createdatetime;}

	public LocalDateTime getUpdatedatetime() {return updatedatetime;}
	public void setUpdatedatetime(LocalDateTime updatedatetime) {this.updatedatetime = updatedatetime;}
	
	public Integer getDiasDisponibles() {
        int otorgados = (this.diasOtorgados != null) ? this.diasOtorgados : 0;
        int tomados = (this.diasTomados != null) ? this.diasTomados : 0;
        return otorgados - tomados;
    }
}