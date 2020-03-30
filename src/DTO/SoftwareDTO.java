package DTO;

public class SoftwareDTO extends ComponenteDTO
{
    private String codLiciencia;
    

    
    public SoftwareDTO(int idComponente, int idEquipo, int idStock, int idProveedor, String descripcion,
			String codLiciencia) {
		super(idComponente, idEquipo, idStock, idProveedor, descripcion);
		this.codLiciencia = codLiciencia;
	}

	public String getCodLiciencia() {
        return this.codLiciencia;
    }
    
    public void setCodLiciencia(final String codLiciencia) {
        this.codLiciencia = codLiciencia;
    }
}
