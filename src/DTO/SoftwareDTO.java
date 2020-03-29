package DTO;

public class SoftwareDTO extends ComponenteDTO
{
    private String codLiciencia;
    
    public SoftwareDTO(final String descripcion, final int udsDisponibles, final String codLiciencia) {
        super(descripcion, udsDisponibles);
        this.codLiciencia = codLiciencia;
    }
    
    public String getCodLiciencia() {
        return this.codLiciencia;
    }
    
    public void setCodLiciencia(final String codLiciencia) {
        this.codLiciencia = codLiciencia;
    }
}
