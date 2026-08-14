package nieto.genm.colaborador.dto;

public class PermisosDto {
	
	private int idModulo;
	private int subModulos;
	private int iduser;
	private int idunineg;
	private String modulo;
	private String submodulo;
	
	public String getModulo() {return modulo;}
	public void setModulo(String modulo) {this.modulo = modulo;}
	
	public String getSubmodulo() {return submodulo;}
	public void setSubmodulo(String submodulo) {this.submodulo = submodulo;}
	
	public int getIdModulo() {return idModulo;}
	public void setIdModulo(int idModulo) {this.idModulo = idModulo;}
	
	public int getSubModulos() {return subModulos;}
	public void setSubModulos(int subModulos) {this.subModulos = subModulos;}
	
	public int getIduser() {return iduser;}
	public void setIduser(int iduser) {this.iduser = iduser;}
	
	public int getIdunineg() {return idunineg;}
	public void setIdunineg(int idunineg) {this.idunineg = idunineg;}
	
	@Override
	public String toString() {
		return "PermisosDto [idModulo=" + idModulo + ", subModulos=" + subModulos + ", iduser=" + iduser + ", idunineg="
				+ idunineg + ", modulo=" + modulo + ", submodulo=" + submodulo + "]";
	}

}