package DTO;

public class TecnicoDTO extends UsuarioDTO
{
    private int nIncidencias;
    private IncidenciaDTO[] incidencias;
    
    public TecnicoDTO(final String userName, final String email, IncidenciaDTO[] incidencias, final CentroEstudioDTO centroEstudio, final int nIncidencias, final IncidenciaDTO[] incidencias2) {
        super(userName, email, incidencias, centroEstudio);
        this.nIncidencias = nIncidencias;
        incidencias = incidencias2;
    }
    
    public int getnIncidencias() {
        return this.nIncidencias;
    }
    
    public void setnIncidencias(final int nIncidencias) {
        this.nIncidencias = nIncidencias;
    }
    
    public IncidenciaDTO[] getIncidencias() {
        return this.incidencias;
    }
    
    public void setIncidencias(final IncidenciaDTO[] incidencias) {
        this.incidencias = incidencias;
    }
}