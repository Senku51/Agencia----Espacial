package repositorio;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */

import Entidades.Satelite;
import jakarta.persistence.*;
import java.util.List;

public class SateliteRepositorio {

    private EntityManager em;

    public SateliteRepositorio(EntityManager em) {
        this.em = em;
    }

    public void guardar(Satelite satelite) {
        em.getTransaction().begin();
        em.persist(satelite);
        em.getTransaction().commit();
    }

    public Satelite buscarPorId(int id) {
        return em.find(Satelite.class, id);
    }

    public List<Satelite> listarTodos() {
        return em.createQuery("SELECT s FROM Satelite s", Satelite.class).getResultList();
    }

    // RF-006: Satélites por misión
    public List<Satelite> listarPorMision(int idMision) {
        return em.createQuery(
                        "SELECT s FROM Satelite s WHERE s.mision.idMision = :idMision", Satelite.class)
                .setParameter("idMision", idMision)
                .getResultList();
    }

    public void actualizar(Satelite satelite) {
        em.getTransaction().begin();
        em.merge(satelite);
        em.getTransaction().commit();
    }

    public void eliminar(int id) {
        em.getTransaction().begin();
        Satelite satelite = em.find(Satelite.class, id);
        if (satelite != null) em.remove(satelite);
        em.getTransaction().commit();
    }
}