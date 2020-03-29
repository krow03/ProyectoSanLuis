package DTO;

public class UsuarioDTO
{
    private String userName;
    private String email;
    private IncidenciaDTO[] incidencias;
    private CentroEstudioDTO centroEstudio;
    
    public UsuarioDTO(final String userName, final String email, final IncidenciaDTO[] incidencias, final CentroEstudioDTO centroEstudio) {
        this.userName = userName;
        this.email = email;
        this.incidencias = incidencias;
        this.centroEstudio = centroEstudio;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public IncidenciaDTO[] getIncidencias() {
        return this.incidencias;
    }
    
    public void setIncidencias(final IncidenciaDTO[] incidencias) {
        this.incidencias = incidencias;
    }
    
    public CentroEstudioDTO getCentroEstudio() {
        return this.centroEstudio;
    }
    
    public void setCentroEstudio(final CentroEstudioDTO centroEstudio) {
        this.centroEstudio = centroEstudio;
    }
}