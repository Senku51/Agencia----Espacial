package servicios;

import Entidades.ModeloVehiculo;
import Entidades.VehiculoLanzamiento;
import repositorio.ModeloVehiculoRepositorio;
import repositorio.VehiculoLanzamientoRepositorio;
import java.util.List;

/**
 * Servicio para la entidad VehiculoLanzamiento.
 * Contiene la lógica de negocio y las validaciones antes de delegar
 * las operaciones al repositorio correspondiente.
 *
 * @author Carlos Martin
 */
public class VehiculoLanzamientoServicio {

    private final VehiculoLanzamientoRepositorio repositorio;
    private final ModeloVehiculoRepositorio modeloRepositorio;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public VehiculoLanzamientoServicio(VehiculoLanzamientoRepositorio repositorio,
                                       ModeloVehiculoRepositorio modeloRepositorio) {
        this.repositorio = repositorio;
        this.modeloRepositorio = modeloRepositorio;
    }

    // -------------------------------------------------------------------------
    // Crear
    // -------------------------------------------------------------------------

    /**
     * Registra un nuevo vehículo de lanzamiento asociado a un modelo existente.
     *
     * @param nombreSerial nombre serial del vehículo
     * @param idModelo     identificador del modelo al que pertenece
     * @throws IllegalArgumentException si los datos no son válidos o el modelo no existe
     */
    public void registrarVehiculo(String nombreSerial, int idModelo) {
        if (nombreSerial == null || nombreSerial.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre serial del vehículo no puede estar vacío.");
        }
        ModeloVehiculo modelo = modeloRepositorio.buscarPorId(idModelo);
        if (modelo == null) {
            throw new IllegalArgumentException("No se encontró el ModeloVehiculo con id: " + idModelo);
        }
        VehiculoLanzamiento vehiculo = new VehiculoLanzamiento(nombreSerial.trim(), modelo);
        repositorio.guardar(vehiculo);
    }

    // -------------------------------------------------------------------------
    // Leer
    // -------------------------------------------------------------------------

    /**
     * Obtiene un VehiculoLanzamiento por su identificador.
     *
     * @param id identificador del vehículo
     * @return el VehiculoLanzamiento encontrado o null si no existe
     */
    public VehiculoLanzamiento obtenerPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    /**
     * Devuelve todos los vehículos de lanzamiento registrados.
     *
     * @return lista de todos los VehiculoLanzamiento
     */
    public List<VehiculoLanzamiento> listarTodos() {
        return repositorio.listarTodos();
    }

    // -------------------------------------------------------------------------
    // Actualizar
    // -------------------------------------------------------------------------

    /**
     * Actualiza los datos de un VehiculoLanzamiento identificado por su id.
     *
     * @param id           identificador del vehículo a actualizar
     * @param nombreSerial nuevo nombre serial
     * @param idModelo     nuevo identificador del modelo
     * @throws IllegalArgumentException si el vehículo o el modelo no existen,
     *                                  o los datos no son válidos
     */
    public void actualizarVehiculo(int id, String nombreSerial, int idModelo) {
        VehiculoLanzamiento vehiculo = repositorio.buscarPorId(id);
        if (vehiculo == null) {
            throw new IllegalArgumentException("No se encontró el VehiculoLanzamiento con id: " + id);
        }
        if (nombreSerial == null || nombreSerial.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre serial del vehículo no puede estar vacío.");
        }
        ModeloVehiculo modelo = modeloRepositorio.buscarPorId(idModelo);
        if (modelo == null) {
            throw new IllegalArgumentException("No se encontró el ModeloVehiculo con id: " + idModelo);
        }
        vehiculo.setNombreSerial(nombreSerial.trim());
        vehiculo.setModeloVehiculo(modelo);
        repositorio.actualizar(vehiculo);
    }

    // -------------------------------------------------------------------------
    // Eliminar
    // -------------------------------------------------------------------------

    /**
     * Elimina un VehiculoLanzamiento por su identificador.
     *
     * @param id identificador del vehículo a eliminar
     * @throws IllegalArgumentException si el vehículo no existe
     */
    public void eliminarVehiculo(int id) {
        VehiculoLanzamiento vehiculo = repositorio.buscarPorId(id);
        if (vehiculo == null) {
            throw new IllegalArgumentException("No se encontró el VehiculoLanzamiento con id: " + id);
        }
        repositorio.eliminar(id);
    }
}
