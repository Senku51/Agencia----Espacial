package repositorio;

import Entidades.ModeloVehiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio para la entidad ModeloVehiculo.
 * Proporciona las operaciones CRUD básicas contra la base de datos mediante JPA.
 *
 * @author Carlos Martin
 */
public class ModeloVehiculoRepositorio {

    private final EntityManager em;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public ModeloVehiculoRepositorio(EntityManager em) {
        this.em = em;
    }

    // -------------------------------------------------------------------------
    // Crear
    // -------------------------------------------------------------------------

    /**
     * Persiste un nuevo ModeloVehiculo en la base de datos.
     *
     * @param modelo el modelo de vehículo a guardar
     */
    public void guardar(ModeloVehiculo modelo) {
        em.getTransaction().begin();
        em.persist(modelo);
        em.getTransaction().commit();
    }

    // -------------------------------------------------------------------------
    // Leer
    // -------------------------------------------------------------------------

    /**
     * Busca un ModeloVehiculo por su identificador.
     *
     * @param id identificador del modelo
     * @return el ModeloVehiculo encontrado o null si no existe
     */
    public ModeloVehiculo buscarPorId(int id) {
        return em.find(ModeloVehiculo.class, id);
    }

    /**
     * Devuelve todos los modelos de vehículos almacenados.
     *
     * @return lista de todos los ModeloVehiculo
     */
    public List<ModeloVehiculo> listarTodos() {
        TypedQuery<ModeloVehiculo> query =
                em.createQuery("SELECT m FROM ModeloVehiculo m", ModeloVehiculo.class);
        return query.getResultList();
    }

    // -------------------------------------------------------------------------
    // Actualizar
    // -------------------------------------------------------------------------

    /**
     * Actualiza los datos de un ModeloVehiculo existente.
     *
     * @param modelo el modelo con los datos actualizados
     */
    public void actualizar(ModeloVehiculo modelo) {
        em.getTransaction().begin();
        em.merge(modelo);
        em.getTransaction().commit();
    }

    // -------------------------------------------------------------------------
    // Eliminar
    // -------------------------------------------------------------------------

    /**
     * Elimina un ModeloVehiculo de la base de datos por su identificador.
     *
     * @param id identificador del modelo a eliminar
     */
    public void eliminar(int id) {
        ModeloVehiculo modelo = buscarPorId(id);
        if (modelo != null) {
            em.getTransaction().begin();
            em.remove(modelo);
            em.getTransaction().commit();
        }
    }
}
