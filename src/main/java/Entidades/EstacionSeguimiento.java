package Entidades;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */

import jakarta.persistence.*;

@Entity
@Table(name = "estaciones_seguimiento")
public class EstacionSeguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estacion")
    private int idEstacion;

    @Column(name = "nombre_estacion", length = 100, nullable = false)
    private String nombreEstacion;

    @Column(name = "latitud", nullable = false, precision = 10, scale = 8)
    private double latitud;

    @Column(name = "longitud", nullable = false, precision = 11, scale = 8)
    private double longitud;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    public EstacionSeguimiento() {}

    public EstacionSeguimiento(String nombreEstacion, double latitud, double longitud, Ubicacion ubicacion) {
        this.nombreEstacion = nombreEstacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ubicacion = ubicacion;
    }

    public int getIdEstacion() { return idEstacion; }
    public void setIdEstacion(int idEstacion) { this.idEstacion = idEstacion; }

    public String getNombreEstacion() { return nombreEstacion; }
    public void setNombreEstacion(String nombreEstacion) { this.nombreEstacion = nombreEstacion; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public Ubicacion getUbicacion() { return ubicacion; }
    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public String toString() {
        return "EstacionSeguimiento{id=" + idEstacion + ", nombre='" + nombreEstacion + "', ubicacion=" + ubicacion + "}";
    }
}