package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Docente")
@PrimaryKeyJoinColumn(name = "persona_id") // une con la tabla Persona
public class Docente extends Persona{

    
    private Integer idDocente;

    // Se aplica la relación con Oficina (Many to One)
    @ManyToOne
    @JoinColumn(name = "oficina_id", referencedColumnName = "idOficina")
    private Oficina oficina;

    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(name = "Curso_Docente",
    joinColumns = @JoinColumn(name = "curso_id"),
    inverseJoinColumns = @JoinColumn(name="docente_id"))
    private List<Curso> cursos;
    
    
}
