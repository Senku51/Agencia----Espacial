package Servicios;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import Entidades.Astronauta;
import Repositorio.AstronautaRepositorio;
import java.time.LocalDate;
import java.util.List;

public class AstronautaServicio {

    private AstronautaRepositorio repositorio;

    public AstronautaServicio(AstronautaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // RF-001 + RS-004
    public void crearAstronauta(String nombreCompleto, String nacionalidad, LocalDate fechaNacimiento, String especialidad) {
        if (fechaNacimiento.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("RS-004: La fecha de nacimiento no puede ser futura.");
        if (nombreCompleto == null || nombreCompleto.isBlank())
            throw new IllegalArgumentException("El nombre completo es obligatorio.");
        if (nacionalidad == null || nacionalidad.isBlank())
            throw new IllegalArgumentException("La nacionalidad es obligatoria.");
        if (especialidad == null || especialidad.isBlank())
            throw new IllegalArgumentException("La especialidad es obligatoria.");

        repositorio.guardar(new Astronauta(nombreCompleto, nacionalidad, fechaNacimiento, especialidad));
        System.out.println("Astronauta registrado correctamente.");
    }

    // RF-002
    public Astronauta consultarPorId(int id) {
        Astronauta a = repositorio.buscarPorId(id);
        if (a == null) System.out.println("No se encontró astronauta con ID: " + id);
        return a;
    }

    // RF-003
    public List<Astronauta> listarTodos() {
        return repositorio.listarTodos();
    }

    // RF-004 + RS-004
    public void actualizarAstronauta(int id, String nombreCompleto, String nacionalidad, LocalDate fechaNacimiento, String especialidad) {
        Astronauta a = repositorio.buscarPorId(id);
        if (a == null) throw new IllegalArgumentException("No se encontró astronauta con ID: " + id);
        if (fechaNacimiento.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("RS-004: La fecha de nacimiento no puede ser futura.");

        a.setNombreCompleto(nombreCompleto);
        a.setNacionalidad(nacionalidad);
        a.setFechaNacimiento(fechaNacimiento);
        a.setEspecialidad(especialidad);
        repositorio.actualizar(a);
        System.out.println("Astronauta actualizado correctamente.");
    }

    // RF-005
    public void eliminarAstronauta(int id) {
        if (repositorio.buscarPorId(id) == null)
            throw new IllegalArgumentException("No se encontró astronauta con ID: " + id);
        repositorio.eliminar(id);
        System.out.println("Astronauta eliminado correctamente.");
    }
}