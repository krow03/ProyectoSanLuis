package DTO;

public class AulaDTO
{
    private String rangoIps;
    private String nombre;
    private String descripcion;
    private EquipoDTO[] equipos;
    private boolean disponibilidad;
    
    public AulaDTO(final String rangoIps, final String nombre, final String descripcion, final EquipoDTO[] equipos, final boolean disponibilidad) {
        this.rangoIps = rangoIps;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.equipos = equipos;
        this.disponibilidad = disponibilidad;
    }
    
    public String getRangoIps() {
        return this.rangoIps;
    }
    
    public void setRangoIps(final String rangoIps) {
        this.rangoIps = rangoIps;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    
    public EquipoDTO[] getEquipos() {
        return this.equipos;
    }
    
    public void setEquipos(final EquipoDTO[] equipos) {
        this.equipos = equipos;
    }
    
    public boolean isDisponibilidad() {
        return this.disponibilidad;
    }
    
    public void setDisponibilidad(final boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}