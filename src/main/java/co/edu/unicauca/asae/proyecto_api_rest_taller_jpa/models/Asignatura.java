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
@Table(name = "Asignatura")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsignatura;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "codigo", nullable = false, length = 50, unique = true)
    private String codigo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objAsignatura")
    private List<Curso> cursos;

    // Getters and Setters
}
