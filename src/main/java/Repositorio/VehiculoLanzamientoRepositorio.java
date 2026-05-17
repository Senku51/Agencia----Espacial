package repositorio;

import Entidades.VehiculoLanzamiento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio para la entidad VehiculoLanzamiento.
 * Proporciona las operaciones CRUD básicas contra la base de datos mediante JPA.
 *
 * @author Carlos Martin
 */
public class VehiculoLanzamientoRepositorio {

    private final EntityManager em;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public VehiculoLanzamientoRepositorio(EntityManager em) {
        this.em = em;
    }

    // -------------------------------------------------------------------------
    // Crear
    // -------------------------------------------------------------------------

    /**
     * Persiste un nuevo VehiculoLanzamiento en la base de datos.
     *
     * @param vehiculo el vehículo de lanzamiento a guardar
     */
    public void guardar(VehiculoLanzamiento vehiculo) {
        em.getTransaction().begin();
        em.persist(vehiculo);
        em.getTransaction().commit();
    }

    // -------------------------------------------------------------------------
    // Leer
    // -------------------------------------------------------------------------

    /**
     * Busca un VehiculoLanzamiento por su identificador.
     *
     * @param id identificador del vehículo
     * @return el VehiculoLanzamiento encontrado o null si no existe
     */
    public VehiculoLanzamiento buscarPorId(int id) {
        return em.find(VehiculoLanzamiento.class, id);
    }

    /**
     * Devuelve todos los vehículos de lanzamiento almacenados.
     *
     * @return lista de todos los VehiculoLanzamiento
     */
    public List<VehiculoLanzamiento> listarTodos() {
        TypedQuery<VehiculoLanzamiento> query =
                em.createQuery("SELECT v FROM VehiculoLanzamiento v", VehiculoLanzamiento.class);
        return query.getResultList();
    }

    // -------------------------------------------------------------------------
    // Actualizar
    // -------------------------------------------------------------------------

    /**
     * Actualiza los datos de un VehiculoLanzamiento existente.
     *
     * @param vehiculo el vehículo con los datos actualizados
     */
    public void actualizar(VehiculoLanzamiento vehiculo) {
        em.getTransaction().begin();
        em.merge(vehiculo);
        em.getTransaction().commit();
    }

    // -------------------------------------------------------------------------
    // Eliminar
    // -------------------------------------------------------------------------

    /**
     * Elimina un VehiculoLanzamiento de la base de datos por su identificador.
     *
     * @param id identificador del vehículo a eliminar
     */
    public void eliminar(int id) {
        VehiculoLanzamiento vehiculo = buscarPorId(id);
        if (vehiculo != null) {
            em.getTransaction().begin();
            em.remove(vehiculo);
            em.getTransaction().commit();
        }
    }
}
