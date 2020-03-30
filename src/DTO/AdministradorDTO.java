package DTO;

public class AdministradorDTO extends TecnicoDTO
{
    public AdministradorDTO(String idUsuario, String userName, String email, int idEquipo) {
        super(idUsuario,userName,email,idEquipo);
    }
}