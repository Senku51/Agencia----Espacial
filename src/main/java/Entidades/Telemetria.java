package Entidades;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetria")
public class Telemetria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private long idRegistro;

    @ManyToOne
    @JoinColumn(name = "id_satelite", nullable = false)
    private Satelite satelite;

    @Column(name = "fecha_hora_lectura", nullable = false)
    private LocalDateTime fechaHoraLectura;

    @Column(name = "temperatura", nullable = false)
    private double temperatura;

    @Column(name = "velocidad", nullable = false)
    private double velocidad;

    @Column(name = "nivel_bateria", nullable = false)
    private int nivelBateria;

    public Telemetria() {}

    public Telemetria(Satelite satelite, LocalDateTime fechaHoraLectura, double temperatura, double velocidad, int nivelBateria) {
        this.satelite = satelite;
        this.fechaHoraLectura = fechaHoraLectura;
        this.temperatura = temperatura;
        this.velocidad = velocidad;
        this.nivelBateria = nivelBateria;
    }

    public long getIdRegistro() { return idRegistro; }
    public void setIdRegistro(long idRegistro) { this.idRegistro = idRegistro; }

    public Satelite getSatelite() { return satelite; }
    public void setSatelite(Satelite satelite) { this.satelite = satelite; }

    public LocalDateTime getFechaHoraLectura() { return fechaHoraLectura; }
    public void setFechaHoraLectura(LocalDateTime fechaHoraLectura) { this.fechaHoraLectura = fechaHoraLectura; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public double getVelocidad() { return velocidad; }
    public void setVelocidad(double velocidad) { this.velocidad = velocidad; }

    public int getNivelBateria() { return nivelBateria; }
    public void setNivelBateria(int nivelBateria) { this.nivelBateria = nivelBateria; }

    @Override
    public String toString() {
        return "Telemetria{id=" + idRegistro + ", satelite=" + satelite + ", bateria=" + nivelBateria + "%}";
    }
}