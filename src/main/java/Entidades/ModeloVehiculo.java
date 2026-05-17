package Entidades;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad que representa un modelo de vehículo de lanzamiento.
 * Un mismo modelo puede haber sido utilizado en varios vehículos a lo largo del tiempo.
 *
 * @author Carlos Martin
 */
@Entity
@Table(name = "modelos_vehiculos")
public class ModeloVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private int idModelo;

    @Column(name = "nombre_modelo", length = 100, nullable = false)
    private String nombreModelo;

    @Column(name = "capacidad_carga_kg", nullable = false)
    private double capacidadCargaKg;

    @Column(name = "pais_fabricacion", length = 100)
    private String paisFabricacion;

    @OneToMany(mappedBy = "modeloVehiculo", cascade = CascadeType.ALL)
    private List<VehiculoLanzamiento> vehiculos;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public ModeloVehiculo() {
    }

    public ModeloVehiculo(String nombreModelo, double capacidadCargaKg, String paisFabricacion) {
        this.nombreModelo = nombreModelo;
        this.capacidadCargaKg = capacidadCargaKg;
        this.paisFabricacion = paisFabricacion;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public double getCapacidadCargaKg() {
        return capacidadCargaKg;
    }

    public void setCapacidadCargaKg(double capacidadCargaKg) {
        this.capacidadCargaKg = capacidadCargaKg;
    }

    public String getPaisFabricacion() {
        return paisFabricacion;
    }

    public void setPaisFabricacion(String paisFabricacion) {
        this.paisFabricacion = paisFabricacion;
    }

    public List<VehiculoLanzamiento> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculoLanzamiento> vehiculos) {
        this.vehiculos = vehiculos;
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "ModeloVehiculo{"
                + "idModelo=" + idModelo
                + ", nombreModelo='" + nombreModelo + '\''
                + ", capacidadCargaKg=" + capacidadCargaKg
                + ", paisFabricacion='" + paisFabricacion + '\''
                + '}';
    }
}
