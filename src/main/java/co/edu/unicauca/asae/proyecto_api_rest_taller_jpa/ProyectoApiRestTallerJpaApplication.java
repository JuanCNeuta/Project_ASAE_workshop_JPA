package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Curso;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Docente;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.EspacioFisico;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.FranjaHoraria;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Oficina;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Persona;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.CursosRepository;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.DocentesRepository;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.EspaciosFisicosRepository;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.FranjasHorariasRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ProyectoApiRestTallerJpaApplication implements CommandLineRunner{

	@Autowired
	private DocentesRepository docenteRepository;

	@Autowired
	private CursosRepository servicioDBCurso;

	@Autowired
	private EspaciosFisicosRepository servicioDBEspacioFisico;

	@Autowired
    private FranjasHorariasRepository servicioDBFranjasHorarias;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApiRestTallerJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//crearDocente();
		crearFranjaHoraria(1, 1, "Lunes", Time.valueOf("08:00:00"), Time.valueOf("10:00:00"));
		crearFranjaHoraria(1, 1, "Martes", Time.valueOf("08:00:00"), Time.valueOf("10:00:00"));
		listarFranjasHorarias();
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

	private void crearFranjaHoraria(Integer cursoId, Integer espacioFisicoId, String dia, Time horaInicio, Time horaFin){

		// Buscar el curso
        Optional<Curso> cursoOpt = servicioDBCurso.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            System.out.println("CURSO NO ENCONTRADO");
            return;
        }
        Curso curso = cursoOpt.get();

		// Buscar el espacio físico
        Optional<EspacioFisico> espacioFisicoOpt = servicioDBEspacioFisico.findById(espacioFisicoId);
        if (espacioFisicoOpt.isEmpty()) {
            System.out.println("ESPACIO FISICO NO ENCONTRADO");
            return;
        }
        EspacioFisico espacioFisico = espacioFisicoOpt.get();

		// Crear una nueva franja horaria
        FranjaHoraria franjaHoraria = new FranjaHoraria();
        franjaHoraria.setDia(dia);
        franjaHoraria.setHoraInicio(horaInicio);
        franjaHoraria.setHoraFin(horaFin);

        // Asociar el curso y el espacio físico a la franja horaria
        franjaHoraria.setObjCurso(curso);
        franjaHoraria.setObjEspacioFisico(espacioFisico);

        // Guardar la franja horaria en la base de datos
        servicioDBFranjasHorarias.save(franjaHoraria);

        System.out.println("Franja horaria CREADA y ASOCIADA CORRECTAMENTE.");
	}

	private void listarFranjasHorarias() {
        // Obtener todas las franjas horarias con fetch eager
        List<FranjaHoraria> franjasHorarias = (List<FranjaHoraria>) servicioDBFranjasHorarias.findAll();

        // Imprimir los detalles de las franjas horarias, junto con cursos y espacios físicos asociados
        for (FranjaHoraria franja : franjasHorarias) {
            Curso curso = franja.getObjCurso();
            EspacioFisico espacioFisico = franja.getObjEspacioFisico();

            System.out.println("Franja Horaria:");
            System.out.println("  Día: " + franja.getDia());
            System.out.println("  Hora Inicio: " + franja.getHoraInicio());
            System.out.println("  Hora Fin: " + franja.getHoraFin());

            // Detalles del curso asociado
            if (curso != null) {
                System.out.println("  Curso Asociado: " + curso.getNombre());
            } else {
                System.out.println("  No hay curso asociado.");
            }

            // Detalles del espacio físico asociado
            if (espacioFisico != null) {
                System.out.println("  Espacio Físico Asociado: " + espacioFisico.getNombre());
            } else {
                System.out.println("  No hay espacio físico asociado.");
            }
            System.out.println("---------------------------------------");
        }
    }

}
