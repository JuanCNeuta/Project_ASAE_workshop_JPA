package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Docente;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Oficina;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Persona;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.DocentesRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ProyectoApiRestTallerJpaApplication implements CommandLineRunner{

	@Autowired
	private DocentesRepository docenteRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApiRestTallerJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		crearDocente();
	}

	private void crearDocente(){

        // Creación oficina #1
        Oficina oficina = new Oficina();
        oficina.setNombre("Oficina-204");
        oficina.setUbicacion("Ingeniería");

		// Creación oficina #2
        Oficina oficina2 = new Oficina();
        oficina2.setNombre("Oficina-205");
        oficina2.setUbicacion("Ingeniería");

        // Crear docentes
        Docente docente = new Docente("Juan","Perez","jperez@universidad.edu",22);
		Docente docente2= new Docente("Carlos", "Lopez", "carlos@universidad.edu", 12);
        
		//Asignacion de oficinas a los docentes
        docente.setObjOficina(oficina);
		docente2.setObjOficina(oficina2);

        // Guardar los docentes, lo cual también guardará la persona y la oficina en cascada
        //docenteRepository.save(docente);
		//docenteRepository.save(docente2);

		List<Docente> listaDocentes = new LinkedList();
		listaDocentes.add(docente);
		listaDocentes.add(docente2);

		docenteRepository.saveAll(listaDocentes);

        System.out.println("Docentes, personas y oficinas almacenados con éxito.");
	}

}
