package repositorio;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import Entidades.EstacionSeguimiento;
import jakarta.persistence.*;
import java.util.List;

public class EstacionSeguimientoRepositorio {

    private EntityManager em;

    public EstacionSeguimientoRepositorio(EntityManager em) {
        this.em = em;
    }

    public void guardar(EstacionSeguimiento estacion) {
        em.getTransaction().begin();
        em.persist(estacion);
        em.getTransaction().commit();
    }

    public EstacionSeguimiento buscarPorId(int id) {
        return em.find(EstacionSeguimiento.class, id);
    }

    // RF-003: listar todas las estaciones
    public List<EstacionSeguimiento> listarTodos() {
        return em.createQuery("SELECT e FROM EstacionSeguimiento e", EstacionSeguimiento.class).getResultList();
    }

    public void actualizar(EstacionSeguimiento estacion) {
        em.getTransaction().begin();
        em.merge(estacion);
        em.getTransaction().commit();
    }

    public void eliminar(int id) {
        em.getTransaction().begin();
        EstacionSeguimiento estacion = em.find(EstacionSeguimiento.class, id);
        if (estacion != null) em.remove(estacion);
        em.getTransaction().commit();
    }
}