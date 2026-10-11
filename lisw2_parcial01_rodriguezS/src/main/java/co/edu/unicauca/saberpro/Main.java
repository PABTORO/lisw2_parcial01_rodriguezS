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
        pipeline.addFiltro(new FiltroRespuestaCorrecta());

        // CORREGIDO (Punto 1 - Error 4, línea 21): se pasaba null en lugar del pipeline al servicio.   
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

        // Pregunta con una clave que NO esta entre las opciones para probar el pipeline
        Pregunta p2 = new Pregunta(
            2,
            "¿Qué es un filtro en Pipes and Filters?",
            Arrays.asList("A. Una etapa de procesamiento", "B. Una base de datos", "C. Un framework", "D. Un patrón de UI"),
            "E. Ninguna de las anteriores",
            "Borrador"
        );

        boolean exito2 = service.registrarPregunta(p2);
        if (exito2) {
            System.out.println("Pregunta 2 registrada. Total en banco: " + service.obtenerTodas().size());
        } else {
            System.out.println("Pregunta 2 rechazada: la respuesta correcta no está entre las opciones.");
        }
    }
}
