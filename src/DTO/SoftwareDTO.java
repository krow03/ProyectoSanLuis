package DTO;

public class SoftwareDTO extends ComponenteDTO
{
    private String codLiciencia;
    private double peso;

    
    public SoftwareDTO(int idComponente, int idEquipo, int idStock, int idProveedor, String descripcion,
			String codLiciencia,double peso) {
		super(idComponente, idEquipo, idStock, idProveedor, descripcion);
		this.codLiciencia = codLiciencia;
		this.peso=peso;
	}

	public String getCodLiciencia() {
        return this.codLiciencia;
    }
    
    public void setCodLiciencia(final String codLiciencia) {
        this.codLiciencia = codLiciencia;
    }

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
    
}
