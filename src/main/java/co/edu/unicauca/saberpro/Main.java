package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.model.Pregunta;
import co.edu.unicauca.saberpro.pipeline.*;
import co.edu.unicauca.saberpro.repository.*;
import co.edu.unicauca.saberpro.service.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BANCO DE PREGUNTAS SABER PRO ===");

        // Configuración de dependencias (DIP)
        IPreguntaRepository repository = new PreguntaRepositoryImpl();
        ValidadorPipeline pipeline = new ValidadorPipeline();
        pipeline.addFiltro(new FiltroEnunciado());
        pipeline.addFiltro(new FiltroOpciones());

        IPreguntaService service = new PreguntaServiceImpl(repository, pipeline);

        Pregunta p1 = new Pregunta(
            1,
            "¿Cuál es el objetivo principal del patrón Pipes and Filters?",
            Arrays.asList("A. Dividir proceso en etapas", "B. Crear BD", "C. Usar MVC", "D. Ninguna"),
            "A. Dividir proceso en etapas",
            "Borrador"
        );

        boolean exito = service.registrarPregunta(p1);
        if (exito) {
            System.out.println("Pregunta registrada con éxito. Total en banco: " + service.obtenerTodas().size());
        } else {
            System.out.println("No se pudo registrar la pregunta debido a fallos en el pipeline.");
        }
    }
}