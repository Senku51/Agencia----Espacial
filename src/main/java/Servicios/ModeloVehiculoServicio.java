package servicios;

import Entidades.ModeloVehiculo;
import repositorio.ModeloVehiculoRepositorio;
import java.util.List;

/**
 * Servicio para la entidad ModeloVehiculo.
 * Contiene la lógica de negocio y las validaciones antes de delegar
 * las operaciones al repositorio correspondiente.
 *
 * @author Carlos Martin
 */
public class ModeloVehiculoServicio {

    private final ModeloVehiculoRepositorio repositorio;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public ModeloVehiculoServicio(ModeloVehiculoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // -------------------------------------------------------------------------
    // Crear
    // -------------------------------------------------------------------------

    /**
     * Registra un nuevo modelo de vehículo tras validar los datos obligatorios.
     *
     * @param nombreModelo     nombre del modelo
     * @param capacidadCargaKg capacidad de carga en kilogramos (debe ser positiva)
     * @param paisFabricacion  país de fabricación
     * @throws IllegalArgumentException si algún campo no cumple las validaciones
     */
    public void registrarModelo(String nombreModelo, double capacidadCargaKg, String paisFabricacion) {
        if (nombreModelo == null || nombreModelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del modelo no puede estar vacío.");
        }
        if (capacidadCargaKg <= 0) {
            throw new IllegalArgumentException("La capacidad de carga debe ser un valor positivo.");
        }
        ModeloVehiculo modelo = new ModeloVehiculo(nombreModelo.trim(), capacidadCargaKg, paisFabricacion);
        repositorio.guardar(modelo);
    }

    // -------------------------------------------------------------------------
    // Leer
    // -------------------------------------------------------------------------

    /**
     * Obtiene un ModeloVehiculo por su identificador.
     *
     * @param id identificador del modelo
     * @return el ModeloVehiculo encontrado o null si no existe
     */
    public ModeloVehiculo obtenerPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    /**
     * Devuelve todos los modelos de vehículos registrados.
     *
     * @return lista de todos los ModeloVehiculo
     */
    public List<ModeloVehiculo> listarTodos() {
        return repositorio.listarTodos();
    }

    // -------------------------------------------------------------------------
    // Actualizar
    // -------------------------------------------------------------------------

    /**
     * Actualiza los datos de un ModeloVehiculo identificado por su id.
     *
     * @param id               identificador del modelo a actualizar
     * @param nombreModelo     nuevo nombre del modelo
     * @param capacidadCargaKg nueva capacidad de carga en kilogramos
     * @param paisFabricacion  nuevo país de fabricación
     * @throws IllegalArgumentException si el modelo no existe o los datos no son válidos
     */
    public void actualizarModelo(int id, String nombreModelo, double capacidadCargaKg, String paisFabricacion) {
        ModeloVehiculo modelo = repositorio.buscarPorId(id);
        if (modelo == null) {
            throw new IllegalArgumentException("No se encontró el ModeloVehiculo con id: " + id);
        }
        if (nombreModelo == null || nombreModelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del modelo no puede estar vacío.");
        }
        if (capacidadCargaKg <= 0) {
            throw new IllegalArgumentException("La capacidad de carga debe ser un valor positivo.");
        }
        modelo.setNombreModelo(nombreModelo.trim());
        modelo.setCapacidadCargaKg(capacidadCargaKg);
        modelo.setPaisFabricacion(paisFabricacion);
        repositorio.actualizar(modelo);
    }

    // -------------------------------------------------------------------------
    // Eliminar
    // -------------------------------------------------------------------------

    /**
     * Elimina un ModeloVehiculo por su identificador.
     *
     * @param id identificador del modelo a eliminar
     * @throws IllegalArgumentException si el modelo no existe
     */
    public void eliminarModelo(int id) {
        ModeloVehiculo modelo = repositorio.buscarPorId(id);
        if (modelo == null) {
            throw new IllegalArgumentException("No se encontró el ModeloVehiculo con id: " + id);
        }
        repositorio.eliminar(id);
    }
}
