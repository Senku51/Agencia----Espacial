package Servicios;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import Entidades.Mision;
import Entidades.Satelite;
import Repositorio.SateliteRepositorio;
import java.time.LocalDate;
import java.util.List;

public class SateliteServicio {

    private SateliteRepositorio repositorio;

    public SateliteServicio(SateliteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // RF-001 + RS-002
    public void crearSatelite(String nombreSatelite, String tipo, double altitudOrbitalKm, LocalDate fechaPuestaOrbita, Mision mision) {
        if (altitudOrbitalKm <= 0)
            throw new IllegalArgumentException("RS-002: La altitud orbital debe ser positiva.");
        if (nombreSatelite == null || nombreSatelite.isBlank())
            throw new IllegalArgumentException("El nombre del satélite es obligatorio.");
        if (mision == null)
            throw new IllegalArgumentException("La misión es obligatoria.");

        repositorio.guardar(new Satelite(nombreSatelite, tipo, altitudOrbitalKm, fechaPuestaOrbita, mision));
        System.out.println("Satélite registrado correctamente.");
    }

    // RF-002
    public Satelite consultarPorId(int id) {
        Satelite s = repositorio.buscarPorId(id);
        if (s == null) System.out.println("No se encontró satélite con ID: " + id);
        return s;
    }

    public List<Satelite> listarTodos() {
        return repositorio.listarTodos();
    }

    // RF-006
    public List<Satelite> listarPorMision(int idMision) {
        return repositorio.listarPorMision(idMision);
    }

    // RF-004 + RS-002
    public void actualizarSatelite(int id, String nombreSatelite, String tipo, double altitudOrbitalKm, LocalDate fechaPuestaOrbita, Mision mision) {
        Satelite s = repositorio.buscarPorId(id);
        if (s == null) throw new IllegalArgumentException("No se encontró satélite con ID: " + id);
        if (altitudOrbitalKm <= 0)
            throw new IllegalArgumentException("RS-002: La altitud orbital debe ser positiva.");

        s.setNombreSatelite(nombreSatelite);
        s.setTipo(tipo);
        s.setAltitudOrbitalKm(altitudOrbitalKm);
        s.setFechaPuestaOrbita(fechaPuestaOrbita);
        s.setMision(mision);
        repositorio.actualizar(s);
        System.out.println("Satélite actualizado correctamente.");
    }

    // RF-005
    public void eliminarSatelite(int id) {
        if (repositorio.buscarPorId(id) == null)
            throw new IllegalArgumentException("No se encontró satélite con ID: " + id);
        repositorio.eliminar(id);
        System.out.println("Satélite eliminado correctamente.");
    }
}