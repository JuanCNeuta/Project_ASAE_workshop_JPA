package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Docente;

@Repository
public interface DocentesRepository extends CrudRepository<Docente,Integer>{
    
}
