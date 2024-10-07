package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="Docente")
@PrimaryKeyJoinColumn(name = "persona_id") // une con la tabla Persona
public class Docente extends Persona{


    @OneToOne(cascade =CascadeType.PERSIST)
    @JoinColumn(name = "oficina_id", referencedColumnName = "idOficina")
    private Oficina objOficina;

    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(name = "Curso_Docente",
    joinColumns = @JoinColumn(name = "docente_id"),
    inverseJoinColumns = @JoinColumn(name="curso_id"))
    private List<Curso> cursos;

    public Docente(){
        super();
    }

    public Docente(String nombrePersona, String apellidoPersona, String correoPersona){
        super(nombrePersona, apellidoPersona, correoPersona);
    }

    public void agregarCurso(Curso curso){
        this.cursos.add(curso);
    }
      
}
