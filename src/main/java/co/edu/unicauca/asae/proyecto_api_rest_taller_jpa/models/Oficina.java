package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oficina")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOficina;

    @Column(length = 20, unique = true)
    private String nombre;

    @Column(length = 20)
    private String ubicacion;

    // Relación con Docente (One to Many)
    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL)
    private List<Docente> docentes;

}
