package Entidades;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import jakarta.persistence.*;

@Entity
@Table(name = "satelite_estacion")
@IdClass(SateliteEstacionId.class)
public class SateliteEstacion {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_satelite", nullable = false)
    private Satelite satelite;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_estacion", nullable = false)
    private EstacionSeguimiento estacion;

    public SateliteEstacion() {}

    public SateliteEstacion(Satelite satelite, EstacionSeguimiento estacion) {
        this.satelite = satelite;
        this.estacion = estacion;
    }

    public Satelite getSatelite() { return satelite; }
    public void setSatelite(Satelite satelite) { this.satelite = satelite; }

    public EstacionSeguimiento getEstacion() { return estacion; }
    public void setEstacion(EstacionSeguimiento estacion) { this.estacion = estacion; }

    @Override
    public String toString() {
        return "SateliteEstacion{satelite=" + satelite + ", estacion=" + estacion + "}";
    }
}