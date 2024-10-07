package co.edu.unicauca.asae.proyecto_api_rest_taller_jpa;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Asignatura;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Curso;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Docente;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.EspacioFisico;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.FranjaHoraria;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Oficina;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.models.Persona;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.AsignaturasRepository;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.CursosRepository;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.DocentesRepository;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.EspaciosFisicosRepository;
import co.edu.unicauca.asae.proyecto_api_rest_taller_jpa.repositories.FranjasHorariasRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ProyectoApiRestTallerJpaApplication implements CommandLineRunner {

    @Autowired
    private DocentesRepository servicioDBDocente;

    @Autowired
    private CursosRepository servicioDBCurso;

    @Autowired
    private AsignaturasRepository servicioDBAsignatura;

    @Autowired
    private EspaciosFisicosRepository servicioDBEspacioFisico;

    @Autowired
    private FranjasHorariasRepository servicioDBFranjasHorarias;

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApiRestTallerJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // crearDocente();
        // crearFranjaHoraria(1, 1, "Lunes", Time.valueOf("08:00:00"),
        // Time.valueOf("10:00:00"));
        // crearFranjaHoraria(3, 1, "Martes",
        // Time.valueOf("08:00:00"),Time.valueOf("10:00:00"));
        // listarFranjasHorarias();
        // crearCurso(2, 3);
        // consultarFranjaPorDocente(3);
        // eliminarCurso(2);
    }

    private void crearDocente() {

        // Creación oficina #1
        Oficina oficina = new Oficina();
        oficina.setNombre("Oficina-210");
        oficina.setUbicacion("Educacion");

        // Crear docentes
        Docente objDocente = new Docente("Alejandro", "Realpe", "arealpe@universidad.edu");

        // Asignacion de oficinas a los docentes
        objDocente.setObjOficina(oficina);
        oficina.setObjDocente(objDocente);

        // Guardar los docentes, lo cual también guardará la persona y la oficina en
        // cascada
        // docenteRepository.save(docente);
        // docenteRepository.save(docente2);

        servicioDBDocente.save(objDocente);

        System.out.println("DOCENTE, PERSONA Y OFICINA CREADOS CON EXITO.");
    }

	private void crearCurso(Integer idAsignatura, Integer idDocente){
		List<Docente> listaDocentes = new LinkedList<>();

        // Buscar la asignatura
        Optional<Asignatura> consultaAsignatura = servicioDBAsignatura.findById(idAsignatura);
        if (consultaAsignatura.isEmpty()) {
            System.out.println("ASIGNATURA NO ENCONTRADA");
            return;
        }
        Asignatura objAsignatura = consultaAsignatura.get();

        // Buscar el docente
        Optional<Docente> consultaDocente = servicioDBDocente.findById(idDocente);
        if (consultaDocente.isEmpty()) {
            System.out.println("DOCENTE NO ENCONTRADO");
            return;
        }
        Docente objDocente = consultaDocente.get();

        // Crear curso
        Curso objCurso = new Curso("Fisica");

        objCurso.setObjAsignatura(objAsignatura);
        objCurso.setDocentes(listaDocentes);

        listaDocentes.add(objDocente);

        objCurso.setObjAsignatura(objAsignatura);
        objCurso.setDocentes(listaDocentes);

        // Insertamos en la tabla de muchos a muchos curso_docente añadiendo un curso al
        // docente
        objDocente.agregarCurso(objCurso);

        this.servicioDBCurso.save(objCurso);

        System.out.println("CURSO CREADO CORRECTAMENTE");
    }

    private void crearFranjaHoraria(Integer cursoId, Integer espacioFisicoId, String dia, Time horaInicio,Time horaFin) {

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

        // Imprimir los detalles de las franjas horarias, junto con cursos y espacios
        // físicos asociados
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

    private void consultarFranjaPorDocente(Integer idDocente) {
        // Buscar el docente
        Optional<Docente> consultaDocente = servicioDBDocente.findById(idDocente);
        if (consultaDocente.isPresent()) {

            Docente objDocente = consultaDocente.get();

            List<Curso> listadoCursosDocente = objDocente.getCursos();

            boolean franjasEncontradas = false; // Variable para saber si se encuentran franjas

            if (listadoCursosDocente != null && !listadoCursosDocente.isEmpty()) {

                for (Curso curso : listadoCursosDocente) {

                    List<FranjaHoraria> listaFranjasHorarias = curso.getFranjasHorarias();

                    if (listaFranjasHorarias != null && !listaFranjasHorarias.isEmpty()) {

                        // Se encontro almenos una franja
                        franjasEncontradas = true;

                        // Imprimir los detalles de las franjas horarias, junto con cursos y espacios
                        // físicos asociados al profesor
                        for (FranjaHoraria franja : listaFranjasHorarias) {
                            EspacioFisico espacioFisico = franja.getObjEspacioFisico();

                            System.out.println("#-----------------------------------#");
                            System.out.println("Franja Horaria:");
                            System.out.println("  Id docente: " + objDocente.getIdPersona());
                            System.out.println("  Docente: " + objDocente.getNombrePersona() + " "
                                    + objDocente.getApellidoPersona());
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
                            System.out.println("#-----------------------------------#");

                        }


                    }
                }

                // Si después de recorrer todos los cursos no se encontraron franjas
                if (!franjasEncontradas) {
                    System.out.println("EL DOCENTE NO TIENE FRANJAS HORARIAS ASIGNADAS");
                }

            } else {
                System.out.println("EL DOCENTE NO TIENE CURSOS ASOCIADOS");
                return;
            }

        } else {

            System.out.println("DOCENTE NO ENCONTRADO");
            return;

        }

    }

    private void eliminarCurso(Integer idCurso) {
        Optional<Curso> consultaCurso = this.servicioDBCurso.findById(idCurso);
        if (consultaCurso.isPresent()) {
            Curso objCurso = consultaCurso.get();

            // Desvincular todos los docentes asociados al curso
            for (Docente docente : objCurso.getDocentes()) {
                docente.getCursos().remove(objCurso);
            }
            objCurso.getDocentes().clear(); // Limpiar la lista de docentes en el curso

            // Ahora que las asociaciones están desvinculadas, eliminamos el curso
            this.servicioDBCurso.delete(objCurso);

            System.out.println("CURSO ELIMINADO CORRECTAMENTE");

        } else {
            System.out.println("NO EXISTE UN CURSO CON EL ID PROPORCIONADO");
        }
    }

}
