package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.model.Pregunta;
import co.edu.unicauca.saberpro.pipeline.*;
import co.edu.unicauca.saberpro.repository.*;
import co.edu.unicauca.saberpro.service.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiltroRespuestaCorrectaTest {

    private final FiltroRespuestaCorrecta filtro = new FiltroRespuestaCorrecta();
    private final List<String> opciones = Arrays.asList("A. x", "B. y", "C. z", "D. w");

    @Test
    void aceptaRespuestaPresenteEnOpciones() {
        Pregunta p = new Pregunta(1, "Enunciado", opciones, "B. y", "Borrador");
        assertTrue(filtro.procesar(p));
    }

    @Test
    void rechazaRespuestaAusenteDeOpciones() {
        Pregunta p = new Pregunta(2, "Enunciado", opciones, "E. otra", "Borrador");
        assertFalse(filtro.procesar(p));
    }

    @Test
    void rechazaCoincidenciaNoExacta() {
        Pregunta p = new Pregunta(3, "Enunciado", opciones, "b. y", "Borrador");
        assertFalse(filtro.procesar(p));
    }

    @Test
    void rechazaRespuestaNulaOPreguntaNula() {
        assertFalse(filtro.procesar(new Pregunta(4, "Enunciado", opciones, null, "Borrador")));
        assertFalse(filtro.procesar(null));
    }

    @Test
    void rechazaCuandoNoHayOpciones() {
        Pregunta p = new Pregunta(5, "Enunciado", null, "A. x", "Borrador");
        assertFalse(filtro.procesar(p));
    }

    @Test
    void pipelineRechazaPreguntaConClaveInvalida() {
        IPreguntaRepository repo = new PreguntaRepositoryImpl();
        ValidadorPipeline pipeline = new ValidadorPipeline();
        pipeline.addFiltro(new FiltroEnunciado());
        pipeline.addFiltro(new FiltroOpciones());
        pipeline.addFiltro(new FiltroRespuestaCorrecta());
        IPreguntaService service = new PreguntaServiceImpl(repo, pipeline);

        Pregunta invalida = new Pregunta(6, "Enunciado", opciones, "Z. nada", "Borrador");
        Pregunta valida = new Pregunta(7, "Enunciado", opciones, "A. x", "Borrador");

        assertFalse(service.registrarPregunta(invalida));
        assertTrue(service.registrarPregunta(valida));
        assertEquals(1, service.obtenerTodas().size());
    }
}
