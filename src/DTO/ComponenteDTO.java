package DTO;

public class ComponenteDTO
{
    private String descripcion;
    private int udsDisponibles;
    
    public ComponenteDTO(final String descripcion, final int udsDisponibles) {
        this.descripcion = descripcion;
        this.udsDisponibles = udsDisponibles;
    }
    
    public ComponenteDTO() {
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getUdsDisponibles() {
        return this.udsDisponibles;
    }
    
    public void setUdsDisponibles(final int udsDisponibles) {
        this.udsDisponibles = udsDisponibles;
    }
} 
