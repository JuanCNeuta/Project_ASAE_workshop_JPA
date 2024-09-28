package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "asignatura_id", referencedColumnName = "idAsignatura")
    private Asignatura objAsignatura;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objCurso")
    private List<FranjaHoraria> franjasHorarias;

    /*
     * @ManyToOne
     * 
     * @JoinColumn(name = "docente_id", nullable = false)
     * private Docente docente;
     */

    // Getters and Setters
}
