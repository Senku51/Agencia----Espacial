package Servicios;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import Entidades.EstacionSeguimiento;
import Entidades.Ubicacion;
import repositorio.EstacionSeguimientoRepositorio;
import java.util.List;

public class EstacionSeguimientoServicio {

    private EstacionSeguimientoRepositorio repositorio;

    public EstacionSeguimientoServicio(EstacionSeguimientoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // RF-001
    public void crearEstacion(String nombreEstacion, double latitud, double longitud, Ubicacion ubicacion) {
        if (nombreEstacion == null || nombreEstacion.isBlank())
            throw new IllegalArgumentException("El nombre de la estación es obligatorio.");
        if (latitud < -90 || latitud > 90)
            throw new IllegalArgumentException("La latitud debe estar entre -90 y 90.");
        if (longitud < -180 || longitud > 180)
            throw new IllegalArgumentException("La longitud debe estar entre -180 y 180.");
        if (ubicacion == null)
            throw new IllegalArgumentException("La ubicación es obligatoria.");

        repositorio.guardar(new EstacionSeguimiento(nombreEstacion, latitud, longitud, ubicacion));
        System.out.println("Estación de seguimiento registrada correctamente.");
    }

    // RF-002
    public EstacionSeguimiento consultarPorId(int id) {
        EstacionSeguimiento e = repositorio.buscarPorId(id);
        if (e == null) System.out.println("No se encontró estación con ID: " + id);
        return e;
    }

    // RF-003
    public List<EstacionSeguimiento> listarTodas() {
        return repositorio.listarTodos();
    }

    // RF-004
    public void actualizarEstacion(int id, String nombreEstacion, double latitud, double longitud, Ubicacion ubicacion) {
        EstacionSeguimiento e = repositorio.buscarPorId(id);
        if (e == null) throw new IllegalArgumentException("No se encontró estación con ID: " + id);
        if (latitud < -90 || latitud > 90)
            throw new IllegalArgumentException("La latitud debe estar entre -90 y 90.");
        if (longitud < -180 || longitud > 180)
            throw new IllegalArgumentException("La longitud debe estar entre -180 y 180.");

        e.setNombreEstacion(nombreEstacion);
        e.setLatitud(latitud);
        e.setLongitud(longitud);
        e.setUbicacion(ubicacion);
        repositorio.actualizar(e);
        System.out.println("Estación actualizada correctamente.");
    }

    // RF-005
    public void eliminarEstacion(int id) {
        if (repositorio.buscarPorId(id) == null)
            throw new IllegalArgumentException("No se encontró estación con ID: " + id);
        repositorio.eliminar(id);
        System.out.println("Estación eliminada correctamente.");
    }
}