package Entidades;

/**
 * @author Manuel Jesus Jiménez Pérez
 * @since 2026/05/13
 * @version 2026/05/13
 */

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "astronautas")
public class Astronauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_astronauta")
    private int idAstronauta;

    @Column(name = "nombre_completo", length = 200, nullable = false)
    private String nombreCompleto;

    @Column(name = "nacionalidad", length = 100, nullable = false)
    private String nacionalidad;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "especialidad", length = 100, nullable = false)
    private String especialidad;

    public Astronauta() {}

    public Astronauta(String nombreCompleto, String nacionalidad, LocalDate fechaNacimiento, String especialidad) {
        this.nombreCompleto = nombreCompleto;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.especialidad = especialidad;
    }

    public int getIdAstronauta() { return idAstronauta; }
    public void setIdAstronauta(int idAstronauta) { this.idAstronauta = idAstronauta; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @Override
    public String toString() {
        return "Astronauta{id=" + idAstronauta + ", nombre='" + nombreCompleto + "', especialidad='" + especialidad + "'}";
    }
}