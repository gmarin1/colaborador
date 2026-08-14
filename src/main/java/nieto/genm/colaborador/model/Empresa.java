package nieto.genm.colaborador.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ac_empresa")
public class Empresa {
 
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre", nullable=false, length=100)
    private String nombre = "";

    @Column(name="rfc", length=15)
    private String rfc = "";

    @Column(name="direccion", length=250)
    private String direccion = "";

    @Column(name="telefono", length=12)
    private String telefono = "";

    @Column(name="cp", length=5)
    private String cp = "";

    @Column(name = "estatus", nullable = false)
    private int estatus = 1;

    @Column(name="idpadre", nullable=false)
    private int idpadre = 0;

    @Column(name = "createDateTime", columnDefinition = "TIMESTAMP DEFAULT now()")
    private String createDateTime;

    @Column(name = "updateDateTime", columnDefinition = "TIMESTAMP DEFAULT now()")
    private String updateDateTime;

    @Column(name="alta", nullable=false, length=50)
    private String alta = "";

    @Column(name="baja", nullable=false, length=100)
    private String baja = "";

    @Column(name="cambio", nullable=false, length=200)
    private String cambio = "";
    
    @Column(name="serie", length=50)
    private String serie = "";

    @Column(precision = 5, scale = 2)    
    @ColumnDefault("0")
    private BigDecimal descuento_autorizar;
  
    @Column(precision = 5, scale = 2)    
    @ColumnDefault("0")
    private BigDecimal descuento_max_comi;
  
    @Column(precision = 5, scale = 2)    
    @ColumnDefault("0")
    private BigDecimal descuento_max_apo;

        
    public Integer getId() {return id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCreateDateTime() {return createDateTime;}
    public void setCreateDateTime(String createDateTime) {this.createDateTime = createDateTime;}

    public String getUpdateDateTime() {return updateDateTime;}
    public void setUpdateDateTime(String updateDateTime) {this.updateDateTime = updateDateTime;}

    public String getAlta() {return alta;}
    public void setAlta(String alta) {this.alta = alta;}

    public String getBaja() {return baja;}
    public void setBaja(String baja) {this.baja = baja;}

    public String getCambio() {return cambio;}
    public void setCambio(String cambio) {this.cambio = cambio;}

    public int getEstatus() {return estatus;}
    public void setEstatus(int estatus) {this.estatus = estatus;}

    public String getRfc() {return rfc;}
    public void setRfc(String rfc) {this.rfc = rfc;}  
    
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getCp() {return cp;}
    public int getIdpadre() {return idpadre;}

    public void setIdpadre(int idpadre) {this.idpadre = idpadre;}
    public String getSerie() {return serie;}

    public void setSerie(String serie) {this.serie = serie;}
    public void setCp(String cp) {this.cp = cp;}
  
    public double getDescuento_autorizar() {return toDouble(descuento_autorizar);}
    public void setDescuento_autorizar(double descuento_autorizar) {this.descuento_autorizar = fromDouble(descuento_autorizar,2);}
  
    public double getDescuento_max_comi() {return toDouble(descuento_max_comi);}
    public void setDescuento_max_comi(double descuento_max_comi) {this.descuento_max_comi = fromDouble(descuento_max_comi,2);}

    public double getDescuento_max_apo() {return toDouble(descuento_max_apo);}
    public void setDescuento_max_apo(double descuento_max_apo) {this.descuento_max_apo = fromDouble(descuento_max_apo,2);}
    
    @Override
    public String toString() {
    	return "Empresa [id=" + id + ", nombre=" + nombre + ", rfc=" + rfc + ", direccion=" + direccion + ", telefono="
    			+ telefono + ", cp=" + cp + ", estatus=" + estatus + ", idpadre=" + idpadre + ", createDateTime="
    			+ createDateTime + ", updateDateTime=" + updateDateTime + ", alta=" + alta + ", baja=" + baja
    			+ ", cambio=" + cambio + ", serie=" + serie + "]";}
      
    private static Double toDouble(BigDecimal bd) {return bd != null ? bd.doubleValue() : null;}
    
    private static BigDecimal fromDouble(Double v, int scale) {
            if (v == null) return null;
            return new BigDecimal(String.valueOf(v)).setScale(scale, RoundingMode.HALF_UP);
    }

}
    