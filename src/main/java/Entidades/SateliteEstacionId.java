package Entidades;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import java.io.Serializable;
import java.util.Objects;

public class SateliteEstacionId implements Serializable {

    private int satelite;
    private int estacion;

    public SateliteEstacionId() {}

    public SateliteEstacionId(int satelite, int estacion) {
        this.satelite = satelite;
        this.estacion = estacion;
    }

    public int getSatelite() { return satelite; }
    public void setSatelite(int satelite) { this.satelite = satelite; }

    public int getEstacion() { return estacion; }
    public void setEstacion(int estacion) { this.estacion = estacion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SateliteEstacionId)) return false;
        SateliteEstacionId that = (SateliteEstacionId) o;
        return satelite == that.satelite && estacion == that.estacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(satelite, estacion);
    }
}