package DTO;

public class EquipoDTO
{
    private String nombre;
    private String ipEquipo;
    private int discoDuro;
    private int disponibleHDD;
    private int ram;
    private ComponenteDTO[] componentes;
    
    public EquipoDTO(final String nombre, final String ipEquipo, final int discoDuro, final int disponibleHDD, final int ram, final ComponenteDTO[] componentes) {
        this.nombre = nombre;
        this.ipEquipo = ipEquipo;
        this.discoDuro = discoDuro;
        this.disponibleHDD = disponibleHDD;
        this.ram = ram;
        this.componentes = componentes;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getIpEquipo() {
        return this.ipEquipo;
    }
    
    public void setIpEquipo(final String ipEquipo) {
        this.ipEquipo = ipEquipo;
    }
    
    public int getDiscoDuro() {
        return this.discoDuro;
    }
    
    public void setDiscoDuro(final int discoDuro) {
        this.discoDuro = discoDuro;
    }
    
    public int getDisponibleHDD() {
        return this.disponibleHDD;
    }
    
    public void setDisponibleHDD(final int disponibleHDD) {
        this.disponibleHDD = disponibleHDD;
    }
    
    public int getRam() {
        return this.ram;
    }
    
    public void setRam(final int ram) {
        this.ram = ram;
    }
    
    public ComponenteDTO[] getComponentes() {
        return this.componentes;
    }
    
    public void setComponentes(final ComponenteDTO[] componentes) {
        this.componentes = componentes;
    }
}
