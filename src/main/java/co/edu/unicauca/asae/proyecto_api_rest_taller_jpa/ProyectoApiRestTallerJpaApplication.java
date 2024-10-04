package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Asignatura;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Curso;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Docente;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Oficina;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.DocentesRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ProyectoApiRestTallerJpaApplication implements CommandLineRunner{

	@Autowired
	private DocentesRepository servicioDBDocente;

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

        // Crear docentes
        Docente objDocente = new Docente("Juan","Perez","jperez@universidad.edu");
        
		//Asignacion de oficinas a los docentes
        objDocente.setObjOficina(oficina);
		oficina.setObjDocente(objDocente);

        // Guardar los docentes, lo cual también guardará la persona y la oficina en cascada
        //docenteRepository.save(docente);
		//docenteRepository.save(docente2);

		servicioDBDocente.save(objDocente);

        System.out.println("Docentes, personas y oficinas almacenados con éxito.");
	}

	public void crearCurso(Integer idAsignatura, Integer idDocente){
		List<Docente> listaDocentes = new LinkedList<>();

		Curso objCurso = new Curso("Programacion");

		Asignatura objAsignatura = new Asignatura("Base de datos", "B1");


		Docente objDocente = new Docente("Carlos", "Osorio", "cosoario@universidad.edu");
		listaDocentes.add(objDocente);
		servicioDBDocente.save(objDocente);

		objCurso.setObjAsignatura(objAsignatura);
		objCurso.setDocentes(listaDocentes);
	}

}
