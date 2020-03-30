package DTO;

public class HardwareDTO extends ComponenteDTO
{
    private String tipo;
    private String marca;
    
    public HardwareDTO(int idComponente, int idEquipo, int idStock, int idProveedor, String descripcion, String tipo,
 			String marca) {
 		super(idComponente, idEquipo, idStock, idProveedor, descripcion);
 		this.tipo = tipo;
 		this.marca = marca;
 	}

    public String getTipo() {
        return this.tipo;
    }

	public void setTipo(final String tipo) {
        this.tipo = tipo;
    }
    
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(final String marca) {
        this.marca = marca;
    }
}