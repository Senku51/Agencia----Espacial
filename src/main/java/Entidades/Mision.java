package Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa una misión espacial.
 * Una misión puede ser tripulada o no tripulada, utiliza un vehículo de lanzamiento
 * y puede tener astronautas asociados mediante la tabla intermedia MisionAstronauta.
 *
 * @author Carlos Martin
 */
@Entity
@Table(name = "misiones")
public class Mision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mision")
    private int idMision;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name = "objetivo_principal", nullable = false)
    private String objetivoPrincipal;

    @Column(name = "fecha_lanzamiento", nullable = false)
    private LocalDate fechaLanzamiento;

    @Column(name = "fecha_fin_prevista")
    private LocalDate fechaFinPrevista;

    @Column(name = "estado_actual", length = 50, nullable = false)
    private String estadoActual;

    @Column(name = "es_tripulada", nullable = false)
    private boolean esTripulada;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculoLanzamiento vehiculoLanzamiento;

    @OneToMany(mappedBy = "mision", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MisionAstronauta> misionAstronautas;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public Mision() {
    }

    public Mision(String nombre, String objetivoPrincipal, LocalDate fechaLanzamiento,
                  LocalDate fechaFinPrevista, String estadoActual, boolean esTripulada,
                  VehiculoLanzamiento vehiculoLanzamiento) {
        this.nombre = nombre;
        this.objetivoPrincipal = objetivoPrincipal;
        this.fechaLanzamiento = fechaLanzamiento;
        this.fechaFinPrevista = fechaFinPrevista;
        this.estadoActual = estadoActual;
        this.esTripulada = esTripulada;
        this.vehiculoLanzamiento = vehiculoLanzamiento;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivoPrincipal() {
        return objetivoPrincipal;
    }

    public void setObjetivoPrincipal(String objetivoPrincipal) {
        this.objetivoPrincipal = objetivoPrincipal;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public LocalDate getFechaFinPrevista() {
        return fechaFinPrevista;
    }

    public void setFechaFinPrevista(LocalDate fechaFinPrevista) {
        this.fechaFinPrevista = fechaFinPrevista;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public boolean isEsTripulada() {
        return esTripulada;
    }

    public void setEsTripulada(boolean esTripulada) {
        this.esTripulada = esTripulada;
    }

    public VehiculoLanzamiento getVehiculoLanzamiento() {
        return vehiculoLanzamiento;
    }

    public void setVehiculoLanzamiento(VehiculoLanzamiento vehiculoLanzamiento) {
        this.vehiculoLanzamiento = vehiculoLanzamiento;
    }

    public List<MisionAstronauta> getMisionAstronautas() {
        return misionAstronautas;
    }

    public void setMisionAstronautas(List<MisionAstronauta> misionAstronautas) {
        this.misionAstronautas = misionAstronautas;
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Mision{"
                + "idMision=" + idMision
                + ", nombre='" + nombre + '\''
                + ", objetivoPrincipal='" + objetivoPrincipal + '\''
                + ", fechaLanzamiento=" + fechaLanzamiento
                + ", fechaFinPrevista=" + fechaFinPrevista
                + ", estadoActual='" + estadoActual + '\''
                + ", esTripulada=" + esTripulada
                + ", vehiculo=" + (vehiculoLanzamiento != null ? vehiculoLanzamiento.getNombreSerial() : "null")
                + '}';
    }
}
