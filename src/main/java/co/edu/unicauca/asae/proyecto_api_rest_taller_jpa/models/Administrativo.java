package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "administrativo")
@PrimaryKeyJoinColumn(name = "persona_id") 
public class Administrativo extends Persona{
    

    private Integer idAdministrativo;

    @Column(length = 255)
    private String rol;
}
