package DTO;

public class SolicitudDTO extends IncidenciaDTO
{
    private ComponenteDTO componente;
    
    public SolicitudDTO(final String codigo, final String fechaSol, final String fechaFin, final String estado, final String descripcion, final String prioridad, final ComponenteDTO componente) {
        super(codigo, fechaSol, fechaFin, estado, descripcion, prioridad);
        this.componente = componente;
    }
    
    public ComponenteDTO getComponente() {
        return this.componente;
    }
    
    public void setComponente(final ComponenteDTO componente) {
        this.componente = componente;
    }
}
