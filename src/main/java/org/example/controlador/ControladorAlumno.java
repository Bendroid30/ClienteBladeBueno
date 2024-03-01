package org.example.controlador;

import org.example.controlador.dao.ApiAlumno;
import org.example.interfaz.Menu;
import org.example.modulo.Alumno;
import org.example.modulo.AlumnoExisteException;

import java.util.ArrayList;
import java.util.List;

public class ControladorAlumno implements ICRUD<Alumno> {

    private List<Alumno> listaAlumnos = new ArrayList<>();
    private ApiAlumno apiAlumno = new ApiAlumno();
    private Menu principal;

    public boolean comprobarAlumnoExiste(Alumno alumno) {
        for (Alumno alumnoEscogido : listaAlumnos) {
            if (alumnoEscogido.getDNI().equalsIgnoreCase(alumno.getDNI())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void agregar(Alumno alumno) throws AlumnoExisteException {
        if (comprobarAlumnoExiste(alumno)) {
            throw new AlumnoExisteException("EL ALUMNO YA SE ENCUENTRA EN LA LISTA");
        } else {
            apiAlumno.insertarAlumno(alumno);
        }
    }

    @Override
    public Alumno buscar(Alumno alumno) {
        return apiAlumno.obtenerAlumno(alumno.getId());
    }

    @Override
    public void eliminar(Alumno alumno) {
        apiAlumno.eliminarAlumno(alumno.getId());
    }

    @Override
    public void modificar(long id, Alumno alumno) {
        apiAlumno.modificarAlumno(id, alumno);
    }

    public List<Alumno> listarAlumnos() {
        listaAlumnos.clear();
        listaAlumnos.addAll(apiAlumno.obtenerTodosAlumnos());
        return listaAlumnos;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public ApiAlumno getApiAlumno() {
        return apiAlumno;
    }

    public void setApiAlumno(ApiAlumno apiAlumno) {
        this.apiAlumno = apiAlumno;
    }

    public Menu getPrincipal() {
        return principal;
    }

    public void setPrincipal(Menu principal) {
        this.principal = principal;
    }
}
