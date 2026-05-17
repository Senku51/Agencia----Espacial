package repositorio;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */

import Entidades.Astronauta;
import jakarta.persistence.*;
import java.util.List;

public class AstronautaRepositorio {

    private EntityManager em;

    public AstronautaRepositorio(EntityManager em) {
        this.em = em;
    }

    public void guardar(Astronauta astronauta) {
        em.getTransaction().begin();
        em.persist(astronauta);
        em.getTransaction().commit();
    }

    public Astronauta buscarPorId(int id) {
        return em.find(Astronauta.class, id);
    }

    public List<Astronauta> listarTodos() {
        return em.createQuery("SELECT a FROM Astronauta a", Astronauta.class).getResultList();
    }

    public void actualizar(Astronauta astronauta) {
        em.getTransaction().begin();
        em.merge(astronauta);
        em.getTransaction().commit();
    }

    public void eliminar(int id) {
        em.getTransaction().begin();
        Astronauta astronauta = em.find(Astronauta.class, id);
        if (astronauta != null) em.remove(astronauta);
        em.getTransaction().commit();
    }
}