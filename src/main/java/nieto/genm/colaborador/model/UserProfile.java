package nieto.genm.colaborador.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

 
@Entity
@Table(name="ac_roles")
public class UserProfile implements Serializable{
	
	private static final long serialVersionUID = -3019225541554992527L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="rol", nullable=false)
	private String rol;
	
	@Column(name="estatus", nullable=false)
	private int estatus = 1;
	
	@Column(name="descripcion", nullable=false)
	private String descripcion;
	
	@Column(name="idmodulo", nullable=false)
	private int idmodulo = 1;
	
	@CreationTimestamp
	@Column(name = "createDateTime", updatable = false)
	private LocalDateTime createDateTime;
	
	@UpdateTimestamp
	@Column(name = "updateDateTime")
	private LocalDateTime updateDateTime;
	
	//getters and setters
	
	public int getId() {return id;}
	  
	public String getRol() {return rol;}
	public void setRol(String rol) {this.rol = rol;}
	  
	public int getEstatus() {return estatus;}
	public void setEstatus(int estatus) {this.estatus = estatus;}
	  
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	  
	public int getIdmodulo() {return idmodulo;}
	public void setIdmodulo(int idmodulo) {this.idmodulo = idmodulo;}
	  
	public LocalDateTime getCreateDateTime() {return createDateTime;}
	public void setCreateDateTime(LocalDateTime createDateTime) {this.createDateTime = createDateTime;}
	  
	public LocalDateTime getUpdateDateTime() {return updateDateTime;}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {this.updateDateTime = updateDateTime;}
	  
	public static long getSerialversionuid() {return serialVersionUID;}
	    
}