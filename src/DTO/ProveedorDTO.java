package DTO;

public class ProveedorDTO
{
    private String cif;
    private String nombre;
    private String email;
    private ComponenteDTO componente;
    
    public ProveedorDTO(final String cif, final String nombre, final String email) {
        this.cif = cif;
        this.nombre = nombre;
        this.email = email;
        this.componente = new ComponenteDTO();
    }
    
    public String getCif() {
        return this.cif;
    }
    
    public void setCif(final String cif) {
        this.cif = cif;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public ComponenteDTO getComponente() {
        return this.componente;
    }
    
    public void setComponente(final ComponenteDTO componente) {
        this.componente = componente;
    }
}