package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave primaria compuesta de la tabla mision_astronauta.
 * Está formada por el identificador de la misión y el identificador del astronauta.
 *
 * @author Carlos Martin
 */
@Embeddable
public class MisionAstronautaId implements Serializable {

    @Column(name = "id_mision")
    private int idMision;

    @Column(name = "id_astronauta")
    private int idAstronauta;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public MisionAstronautaId() {
    }

    public MisionAstronautaId(int idMision, int idAstronauta) {
        this.idMision = idMision;
        this.idAstronauta = idAstronauta;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public int getIdMision() {
        return idMision;
    }

    public void setIdMision(int idMision) {
        this.idMision = idMision;
    }

    public int getIdAstronauta() {
        return idAstronauta;
    }

    public void setIdAstronauta(int idAstronauta) {
        this.idAstronauta = idAstronauta;
    }

    // -------------------------------------------------------------------------
    // equals y hashCode (obligatorios en claves compuestas)
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MisionAstronautaId)) return false;
        MisionAstronautaId that = (MisionAstronautaId) o;
        return idMision == that.idMision && idAstronauta == that.idAstronauta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMision, idAstronauta);
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "MisionAstronautaId{"
                + "idMision=" + idMision
                + ", idAstronauta=" + idAstronauta
                + '}';
    }
}
