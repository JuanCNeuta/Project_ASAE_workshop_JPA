package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Espacio_Fisico")
public class EspacioFisico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspacioFisico;

    @Column(name = "nombre", nullable = false, length = 255, unique = true)
    private String nombre;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objEspacioFisico")
    private List<FranjaHoraria> franjasHorarias;

    // Getters and Setters
}
