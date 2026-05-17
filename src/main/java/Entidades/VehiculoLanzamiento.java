package Entidades;

import jakarta.persistence.*;

/**
 * Entidad que representa un vehículo de lanzamiento concreto (instancia física).
 * Cada vehículo tiene un número serial único y está asociado a un modelo.
 * Cada misión utiliza un vehículo de lanzamiento.
 *
 * @author Carlos Martin
 */
@Entity
@Table(name = "vehiculos_lanzamiento")
public class VehiculoLanzamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private int idVehiculo;

    @Column(name = "nombre_serial", length = 100, nullable = false)
    private String nombreSerial;

    @ManyToOne
    @JoinColumn(name = "id_modelo", nullable = false)
    private ModeloVehiculo modeloVehiculo;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public VehiculoLanzamiento() {
    }

    public VehiculoLanzamiento(String nombreSerial, ModeloVehiculo modeloVehiculo) {
        this.nombreSerial = nombreSerial;
        this.modeloVehiculo = modeloVehiculo;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNombreSerial() {
        return nombreSerial;
    }

    public void setNombreSerial(String nombreSerial) {
        this.nombreSerial = nombreSerial;
    }

    public ModeloVehiculo getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(ModeloVehiculo modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "VehiculoLanzamiento{"
                + "idVehiculo=" + idVehiculo
                + ", nombreSerial='" + nombreSerial + '\''
                + ", modelo=" + (modeloVehiculo != null ? modeloVehiculo.getNombreModelo() : "null")
                + '}';
    }
}
