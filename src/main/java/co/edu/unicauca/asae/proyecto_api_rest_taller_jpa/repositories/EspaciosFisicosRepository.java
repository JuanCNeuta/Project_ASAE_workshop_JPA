package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.EspacioFisico;

public interface EspaciosFisicosRepository extends CrudRepository<EspacioFisico,Integer> {
    
}
