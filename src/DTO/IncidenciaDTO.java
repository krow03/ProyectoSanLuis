package DTO;

public class IncidenciaDTO
{
    private String codigo;
    private String fechaSol;
    private String fechaFin;
    private String estado;
    private String descripcion;
    private String prioridad;
    
    public IncidenciaDTO(final String codigo, final String fechaSol, final String fechaFin, final String estado, final String descripcion, final String prioridad) {
        this.codigo = codigo;
        this.fechaSol = fechaSol;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }
    
    public String getFechaSol() {
        return this.fechaSol;
    }
    
    public void setFechaSol(final String fechaSol) {
        this.fechaSol = fechaSol;
    }
    
    public String getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(final String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(final String estado) {
        this.estado = estado;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getPrioridad() {
        return this.prioridad;
    }
    
    public void setPrioridad(final String prioridad) {
        this.prioridad = prioridad;
    }
}
