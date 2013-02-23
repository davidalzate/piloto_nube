package co.com.nuevaera.gae.services.client.dto;



public class EmisionDto {
	
	private long idEmision;
	private long idRestaurante;
	private boolean anuncionInicial;
	private String propiedad;

	public void setIdEmision(long idEmision){
		this.idEmision=idEmision;
	}
	
	public long getIdEmision() {
		return this.idEmision;
	}
	public long getIdRestaurante() {
		return idRestaurante;
	}
	public void setIdRestaurante(long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}
	public boolean isAnuncionInicial() {
		return anuncionInicial;
	}
	public void setAnuncionInicial(boolean anuncionInicial) {
		this.anuncionInicial = anuncionInicial;
	}
	public String getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}
	
}
