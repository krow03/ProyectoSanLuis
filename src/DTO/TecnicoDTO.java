package DTO;

import java.util.ArrayList;

import org.omg.CORBA.portable.IndirectionException;

public class TecnicoDTO extends UsuarioDTO
{
	//campo calculado
    private int nIncidencias;
    //campo calculado
    private int numAulas;
    
    private ArrayList<IncidenciaDTO> incidenciasAsignadas;
    
    public TecnicoDTO(String idUsuario, String userName, String email, int idEquipo,String pass,String nombre,String apellidos,String direccion,String telefono) {
        super(idUsuario,userName,email,idEquipo,pass,nombre,apellidos, direccion, telefono);
    }
    public TecnicoDTO(String idUsuario, String userName, String email, String pass,String nombre,String apellidos,String direccion,String telefono) {
        super(idUsuario,userName,email,pass,nombre,apellidos, direccion, telefono);
    }
	public int getnIncidencias() {
		return nIncidencias;
	}

	public void setnIncidencias(int nIncidencias) {
		this.nIncidencias = nIncidencias;
	}

	public int getNumAulas() {
		return numAulas;
	}

	public void setNumAulas(int numAulas) {
		this.numAulas = numAulas;
	}

	public ArrayList<IncidenciaDTO> getIncidenciasAsignadas() {

		return incidenciasAsignadas;
	}
	public ArrayList<IncidenciaDTO> getIncidenciasAsignadasNoAtendidas() {
		ArrayList<IncidenciaDTO> noAtendidas = new ArrayList<IncidenciaDTO>();
		System.out.println(incidenciasAsignadas.size());
		for (int i = 0; i < incidenciasAsignadas.size(); i++) {
			System.out.println(incidenciasAsignadas.get(i).getEstado());
			if(!incidenciasAsignadas.get(i).getEstado().equals("atendida")) {
				noAtendidas.add(incidenciasAsignadas.get(i));
			}
		}
		return noAtendidas;
	}
	public void setIncidenciasAsignadas(ArrayList<IncidenciaDTO> incidenciasAsignadas) {
		this.incidenciasAsignadas = incidenciasAsignadas;
	}


}