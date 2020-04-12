package DTO;

public class AdministradorDTO extends TecnicoDTO
{
    public AdministradorDTO(String idUsuario, String userName, String email, int idEquipo,String pass,String nombre,String apellidos,String direccion,String telefono) {
        super(idUsuario,userName,email,idEquipo,pass,nombre,apellidos, direccion, telefono);
    }
}