package DTO;

import java.util.Arrays;

public class TecnicoDTO extends UsuarioDTO
{
    private int nIncidencias;
    private IncidenciaDTO[] incidenciasAsignadas;
    
    public TecnicoDTO(String idUsuario, String userName, String email, int idEquipo) {
        super(idUsuario,userName,email,idEquipo);
    }
    
    public int getnIncidencias() {
        return this.nIncidencias;
    }
    
    public void setnIncidencias(final int nIncidencias) {
        this.nIncidencias = nIncidencias;
    }
    
    public IncidenciaDTO[] getIncidencias() {
        return this.incidenciasAsignadas;
    }
    
    public void setIncidencias(final IncidenciaDTO[] incidencias) {
        this.incidenciasAsignadas = incidencias;
    }

}