package DTO;

public class AdministradorDTO extends TecnicoDTO
{
    public AdministradorDTO(final String userName, final String email, final IncidenciaDTO[] incidencias, final CentroEstudioDTO centroEstudio, final int nIncidencias, final IncidenciaDTO[] incidencias2) {
        super(userName, email, incidencias, centroEstudio, nIncidencias, incidencias2);
    }
}