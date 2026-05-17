package Entidades;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */

//Mi compañero tiene que crear Mision
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "satelites")
public class Satelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_satelite")
    private int idSatelite;

    @Column(name = "nombre_satelite", length = 100, nullable = false)
    private String nombreSatelite;

    @Column(name = "tipo", length = 100, nullable = false)
    private String tipo;

    @Column(name = "altitud_orbital_km", nullable = false)
    private double altitudOrbitalKm;

    @Column(name = "fecha_puesta_orbita")
    private LocalDate fechaPuestaOrbita;

    @ManyToOne
    @JoinColumn(name = "id_mision", nullable = false)
    private Mision mision;

    public Satelite() {}

    public Satelite(String nombreSatelite, String tipo, double altitudOrbitalKm, LocalDate fechaPuestaOrbita, Mision mision) {
        this.nombreSatelite = nombreSatelite;
        this.tipo = tipo;
        this.altitudOrbitalKm = altitudOrbitalKm;
        this.fechaPuestaOrbita = fechaPuestaOrbita;
        this.mision = mision;
    }

    public int getIdSatelite() { return idSatelite; }
    public void setIdSatelite(int idSatelite) { this.idSatelite = idSatelite; }

    public String getNombreSatelite() { return nombreSatelite; }
    public void setNombreSatelite(String nombreSatelite) { this.nombreSatelite = nombreSatelite; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getAltitudOrbitalKm() { return altitudOrbitalKm; }
    public void setAltitudOrbitalKm(double altitudOrbitalKm) { this.altitudOrbitalKm = altitudOrbitalKm; }

    public LocalDate getFechaPuestaOrbita() { return fechaPuestaOrbita; }
    public void setFechaPuestaOrbita(LocalDate fechaPuestaOrbita) { this.fechaPuestaOrbita = fechaPuestaOrbita; }

    public Mision getMision() { return mision; }
    public void setMision(Mision mision) { this.mision = mision; }

    @Override
    public String toString() {
        return "Satelite{id=" + idSatelite + ", nombre='" + nombreSatelite + "', altitud=" + altitudOrbitalKm + "km}";
    }
}