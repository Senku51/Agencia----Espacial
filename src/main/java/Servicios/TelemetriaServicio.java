package Servicios;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import Entidades.Satelite;
import Entidades.Telemetria;
import Repositorio.TelemetriaRepositorio;
import java.time.LocalDateTime;
import java.util.List;

public class TelemetriaServicio {

    private TelemetriaRepositorio repositorio;

    public TelemetriaServicio(TelemetriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // RF-001 + RS-003
    public void crearTelemetria(Satelite satelite, LocalDateTime fechaHoraLectura, double temperatura, double velocidad, int nivelBateria) {
        if (nivelBateria < 0 || nivelBateria > 100)
            throw new IllegalArgumentException("RS-003: El nivel de batería debe estar entre 0 y 100.");
        if (satelite == null)
            throw new IllegalArgumentException("El satélite es obligatorio.");
        if (fechaHoraLectura == null)
            throw new IllegalArgumentException("La fecha y hora de lectura son obligatorias.");

        repositorio.guardar(new Telemetria(satelite, fechaHoraLectura, temperatura, velocidad, nivelBateria));
        System.out.println("Registro de telemetría guardado correctamente.");
    }

    // RF-002: ÚNICO acceso posible — por ID
    public Telemetria consultarPorId(long id) {
        Telemetria t = repositorio.buscarPorId(id);
        if (t == null) System.out.println("No se encontró registro de telemetría con ID: " + id);
        return t;
    }

    // RF-007
    public List<Telemetria> listarPorSatelite(int idSatelite) {
        return repositorio.listarPorSatelite(idSatelite);
    }

    // RF-004 + RS-003
    public void actualizarTelemetria(long id, Satelite satelite, LocalDateTime fechaHoraLectura, double temperatura, double velocidad, int nivelBateria) {
        Telemetria t = repositorio.buscarPorId(id);
        if (t == null) throw new IllegalArgumentException("No se encontró registro con ID: " + id);
        if (nivelBateria < 0 || nivelBateria > 100)
            throw new IllegalArgumentException("RS-003: El nivel de batería debe estar entre 0 y 100.");

        t.setSatelite(satelite);
        t.setFechaHoraLectura(fechaHoraLectura);
        t.setTemperatura(temperatura);
        t.setVelocidad(velocidad);
        t.setNivelBateria(nivelBateria);
        repositorio.actualizar(t);
        System.out.println("Telemetría actualizada correctamente.");
    }

    // RF-005
    public void eliminarTelemetria(long id) {
        if (repositorio.buscarPorId(id) == null)
            throw new IllegalArgumentException("No se encontró registro con ID: " + id);
        repositorio.eliminar(id);
        System.out.println("Registro de telemetría eliminado correctamente.");
    }
}