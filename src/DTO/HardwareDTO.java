package DTO;

public class HardwareDTO extends ComponenteDTO
{
    private String tipo;
    private String marca;
    
    public HardwareDTO(final String descripcion, final int udsDisponibles, final String tipo, final String marca) {
        super(descripcion, udsDisponibles);
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