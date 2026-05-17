package repositorio;

import Entidades.Mision;
import Entidades.MisionAstronauta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio para la entidad Mision.
 * Proporciona operaciones CRUD y consultas específicas del enunciado:
 * - Listado de todas las misiones (RF-003)
 * - Consulta de astronautas por misión (RF-008)
 *
 * @author Carlos Martin
 */
public class MisionRepositorio {

    private final EntityManager em;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public MisionRepositorio(EntityManager em) {
        this.em = em;
    }

    // -------------------------------------------------------------------------
    // Crear
    // -------------------------------------------------------------------------

    /**
     * Persiste una nueva Mision en la base de datos.
     *
     * @param mision la misión a guardar
     */
    public void guardar(Mision mision) {
        em.getTransaction().begin();
        em.persist(mision);
        em.getTransaction().commit();
    }

    // -------------------------------------------------------------------------
    // Leer
    // -------------------------------------------------------------------------

    /**
     * Busca una Mision por su identificador.
     *
     * @param id identificador de la misión
     * @return la Mision encontrada o null si no existe
     */
    public Mision buscarPorId(int id) {
        return em.find(Mision.class, id);
    }

    /**
     * Busca una Mision por su nombre (único según RS-005).
     *
     * @param nombre nombre de la misión
     * @return la Mision encontrada o null si no existe
     */
    public Mision buscarPorNombre(String nombre) {
        try {
            TypedQuery<Mision> query = em.createQuery(
                    "SELECT m FROM Mision m WHERE m.nombre = :nombre", Mision.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Devuelve todas las misiones almacenadas.
     *
     * @return lista de todas las Mision
     */
    public List<Mision> listarTodas() {
        TypedQuery<Mision> query =
                em.createQuery("SELECT m FROM Mision m", Mision.class);
        return query.getResultList();
    }

    /**
     * Devuelve todos los registros de MisionAstronauta de una misión concreta,
     * incluyendo el rol de cada astronauta (RF-008).
     *
     * @param idMision identificador de la misión
     * @return lista de MisionAstronauta de la misión indicada
     */
    public List<MisionAstronauta> listarAstronautasPorMision(int idMision) {
        TypedQuery<MisionAstronauta> query = em.createQuery(
                "SELECT ma FROM MisionAstronauta ma WHERE ma.mision.idMision = :idMision",
                MisionAstronauta.class);
        query.setParameter("idMision", idMision);
        return query.getResultList();
    }

    // -------------------------------------------------------------------------
    // Actualizar
    // -------------------------------------------------------------------------

    /**
     * Actualiza los datos de una Mision existente.
     *
     * @param mision la misión con los datos actualizados
     */
    public void actualizar(Mision mision) {
        em.getTransaction().begin();
        em.merge(mision);
        em.getTransaction().commit();
    }

    // -------------------------------------------------------------------------
    // Eliminar
    // -------------------------------------------------------------------------

    /**
     * Elimina una Mision de la base de datos por su identificador.
     *
     * @param id identificador de la misión a eliminar
     */
    public void eliminar(int id) {
        Mision mision = buscarPorId(id);
        if (mision != null) {
            em.getTransaction().begin();
            em.remove(mision);
            em.getTransaction().commit();
        }
    }
}
