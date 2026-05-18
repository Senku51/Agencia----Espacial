package Repositorio;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import Entidades.Telemetria;
import jakarta.persistence.*;
import java.util.List;

public class TelemetriaRepositorio {

    private EntityManager em;

    public TelemetriaRepositorio(EntityManager em) {
        this.em = em;
    }

    public void guardar(Telemetria telemetria) {
        em.getTransaction().begin();
        em.persist(telemetria);
        em.getTransaction().commit();
    }

    // RF-002: acceso SOLO por ID único
    public Telemetria buscarPorId(long id) {
        return em.find(Telemetria.class, id);
    }

    // RF-007: telemetría por satélite
    public List<Telemetria> listarPorSatelite(int idSatelite) {
        return em.createQuery(
                        "SELECT t FROM Telemetria t WHERE t.satelite.idSatelite = :idSatelite", Telemetria.class)
                .setParameter("idSatelite", idSatelite)
                .getResultList();
    }

    public void actualizar(Telemetria telemetria) {
        em.getTransaction().begin();
        em.merge(telemetria);
        em.getTransaction().commit();
    }

    public void eliminar(long id) {
        em.getTransaction().begin();
        Telemetria telemetria = em.find(Telemetria.class, id);
        if (telemetria != null) em.remove(telemetria);
        em.getTransaction().commit();
    }
}