package servicios;

import Entidades.Mision;
import Entidades.MisionAstronauta;
import Entidades.VehiculoLanzamiento;
import repositorio.MisionRepositorio;
import repositorio.VehiculoLanzamientoRepositorio;
import java.time.LocalDate;
import java.util.List;

/**
 * Servicio para la entidad Mision.
 * Contiene la lógica de negocio y las validaciones definidas en los requisitos:
 * RS-001 (campos obligatorios), RS-005 (unicidad del nombre).
 * También implementa las consultas RF-003, RF-008.
 *
 * @author Carlos Martin
 */
public class MisionServicio {

    private final MisionRepositorio repositorio;
    private final VehiculoLanzamientoRepositorio vehiculoRepositorio;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public MisionServicio(MisionRepositorio repositorio,
                          VehiculoLanzamientoRepositorio vehiculoRepositorio) {
        this.repositorio = repositorio;
        this.vehiculoRepositorio = vehiculoRepositorio;
    }

    // -------------------------------------------------------------------------
    // Crear
    // -------------------------------------------------------------------------

    /**
     * Registra una nueva misión tras validar los campos obligatorios y la unicidad
     * del nombre (RS-001, RS-005).
     *
     * @param nombre            nombre único de la misión
     * @param objetivoPrincipal objetivo principal de la misión
     * @param fechaLanzamiento  fecha de lanzamiento (no puede ser null)
     * @param fechaFinPrevista  fecha de finalización prevista (puede ser null)
     * @param estadoActual      estado actual de la misión (no puede estar vacío)
     * @param esTripulada       indica si la misión es tripulada
     * @param idVehiculo        identificador del vehículo de lanzamiento asignado
     * @throws IllegalArgumentException si algún campo obligatorio falta,
     *                                  ya existe una misión con ese nombre
     *                                  o el vehículo no existe
     */
    public void registrarMision(String nombre, String objetivoPrincipal,
                                LocalDate fechaLanzamiento, LocalDate fechaFinPrevista,
                                String estadoActual, boolean esTripulada, int idVehiculo) {
        // RS-001: validación de campos obligatorios
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la misión no puede estar vacío.");
        }
        if (fechaLanzamiento == null) {
            throw new IllegalArgumentException("La fecha de lanzamiento es obligatoria.");
        }
        if (estadoActual == null || estadoActual.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado actual de la misión no puede estar vacío.");
        }

        // RS-005: unicidad del nombre
        if (repositorio.buscarPorNombre(nombre.trim()) != null) {
            throw new IllegalArgumentException("Ya existe una misión con el nombre: " + nombre);
        }

        VehiculoLanzamiento vehiculo = vehiculoRepositorio.buscarPorId(idVehiculo);
        if (vehiculo == null) {
            throw new IllegalArgumentException("No se encontró el VehiculoLanzamiento con id: " + idVehiculo);
        }

        Mision mision = new Mision(nombre.trim(), objetivoPrincipal, fechaLanzamiento,
                fechaFinPrevista, estadoActual.trim(), esTripulada, vehiculo);
        repositorio.guardar(mision);
    }

    // -------------------------------------------------------------------------
    // Leer
    // -------------------------------------------------------------------------

    /**
     * Obtiene una Mision por su identificador (RF-002).
     *
     * @param id identificador de la misión
     * @return la Mision encontrada o null si no existe
     */
    public Mision obtenerPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    /**
     * Devuelve todas las misiones registradas (RF-003).
     *
     * @return lista de todas las Mision
     */
    public List<Mision> listarTodas() {
        return repositorio.listarTodas();
    }

    /**
     * Devuelve los astronautas y su rol en una misión concreta (RF-008).
     *
     * @param idMision identificador de la misión
     * @return lista de MisionAstronauta con el astronauta y su rol
     * @throws IllegalArgumentException si la misión no existe
     */
    public List<MisionAstronauta> listarAstronautasPorMision(int idMision) {
        if (repositorio.buscarPorId(idMision) == null) {
            throw new IllegalArgumentException("No se encontró la Mision con id: " + idMision);
        }
        return repositorio.listarAstronautasPorMision(idMision);
    }

    // -------------------------------------------------------------------------
    // Actualizar
    // -------------------------------------------------------------------------

    /**
     * Actualiza los datos de una Mision identificada por su id.
     * Valida campos obligatorios (RS-001) y unicidad del nombre si cambia (RS-005).
     *
     * @param id                identificador de la misión a actualizar
     * @param nombre            nuevo nombre
     * @param objetivoPrincipal nuevo objetivo principal
     * @param fechaLanzamiento  nueva fecha de lanzamiento
     * @param fechaFinPrevista  nueva fecha de finalización prevista
     * @param estadoActual      nuevo estado actual
     * @param esTripulada       nuevo valor de tripulación
     * @param idVehiculo        nuevo identificador del vehículo de lanzamiento
     * @throws IllegalArgumentException si los datos no son válidos
     */
    public void actualizarMision(int id, String nombre, String objetivoPrincipal,
                                 LocalDate fechaLanzamiento, LocalDate fechaFinPrevista,
                                 String estadoActual, boolean esTripulada, int idVehiculo) {
        Mision mision = repositorio.buscarPorId(id);
        if (mision == null) {
            throw new IllegalArgumentException("No se encontró la Mision con id: " + id);
        }

        // RS-001
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la misión no puede estar vacío.");
        }
        if (fechaLanzamiento == null) {
            throw new IllegalArgumentException("La fecha de lanzamiento es obligatoria.");
        }
        if (estadoActual == null || estadoActual.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado actual de la misión no puede estar vacío.");
        }

        // RS-005: solo comprobar unicidad si el nombre ha cambiado
        if (!nombre.trim().equalsIgnoreCase(mision.getNombre())) {
            if (repositorio.buscarPorNombre(nombre.trim()) != null) {
                throw new IllegalArgumentException("Ya existe una misión con el nombre: " + nombre);
            }
        }

        VehiculoLanzamiento vehiculo = vehiculoRepositorio.buscarPorId(idVehiculo);
        if (vehiculo == null) {
            throw new IllegalArgumentException("No se encontró el VehiculoLanzamiento con id: " + idVehiculo);
        }

        mision.setNombre(nombre.trim());
        mision.setObjetivoPrincipal(objetivoPrincipal);
        mision.setFechaLanzamiento(fechaLanzamiento);
        mision.setFechaFinPrevista(fechaFinPrevista);
        mision.setEstadoActual(estadoActual.trim());
        mision.setEsTripulada(esTripulada);
        mision.setVehiculoLanzamiento(vehiculo);
        repositorio.actualizar(mision);
    }

    // -------------------------------------------------------------------------
    // Eliminar
    // -------------------------------------------------------------------------

    /**
     * Elimina una Mision por su identificador (RF-005).
     *
     * @param id identificador de la misión a eliminar
     * @throws IllegalArgumentException si la misión no existe
     */
    public void eliminarMision(int id) {
        Mision mision = repositorio.buscarPorId(id);
        if (mision == null) {
            throw new IllegalArgumentException("No se encontró la Mision con id: " + id);
        }
        repositorio.eliminar(id);
    }
}
