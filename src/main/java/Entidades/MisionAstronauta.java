package Entidades;

import jakarta.persistence.*;

/**
 * Entidad que representa la relación entre una misión y un astronauta.
 * Incluye el rol que desempeña el astronauta dentro de la misión concreta.
 * Utiliza clave primaria compuesta formada por id_mision e id_astronauta.
 *
 * @author Carlos Martin
 */
@Entity
@Table(name = "mision_astronauta")
public class MisionAstronauta {

    @EmbeddedId
    private MisionAstronautaId id;

    @ManyToOne
    @MapsId("idMision")
    @JoinColumn(name = "id_mision")
    private Mision mision;

    /**
     * Referencia al astronauta. Se mapea con @MapsId sobre el campo idAstronauta
     * de la clave compuesta. La entidad Astronauta la gestiona otro compañero,
     * por lo que se declara la FK directamente.
     */
    @ManyToOne
    @MapsId("idAstronauta")
    @JoinColumn(name = "id_astronauta")
    private Astronauta astronauta;

    @Column(name = "rol_desempenado", length = 100, nullable = false)
    private String rolDesempenado;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public MisionAstronauta() {
    }

    public MisionAstronauta(Mision mision, Astronauta astronauta, String rolDesempenado) {
        this.id = new MisionAstronautaId(mision.getIdMision(), astronauta.getIdAstronauta());
        this.mision = mision;
        this.astronauta = astronauta;
        this.rolDesempenado = rolDesempenado;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public MisionAstronautaId getId() {
        return id;
    }

    public void setId(MisionAstronautaId id) {
        this.id = id;
    }

    public Mision getMision() {
        return mision;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    public Astronauta getAstronauta() {
        return astronauta;
    }

    public void setAstronauta(Astronauta astronauta) {
        this.astronauta = astronauta;
    }

    public String getRolDesempenado() {
        return rolDesempenado;
    }

    public void setRolDesempenado(String rolDesempenado) {
        this.rolDesempenado = rolDesempenado;
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "MisionAstronauta{"
                + "id=" + id
                + ", rolDesempenado='" + rolDesempenado + '\''
                + '}';
    }
}
