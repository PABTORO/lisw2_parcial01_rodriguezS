package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.model.Pregunta;
import co.edu.unicauca.saberpro.pipeline.*;
import co.edu.unicauca.saberpro.repository.*;
import co.edu.unicauca.saberpro.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PreguntaServiceTest {

    private IPreguntaService service;

    @BeforeEach
    void setUp() {
        IPreguntaRepository repo = new PreguntaRepositoryImpl();
        ValidadorPipeline pipeline = new ValidadorPipeline();
        pipeline.addFiltro(new FiltroEnunciado());
        pipeline.addFiltro(new FiltroOpciones());
        service = new PreguntaServiceImpl(repo, pipeline);
    }

    private Pregunta preguntaValida() {
        return new Pregunta(1, "¿Qué es un filtro?",
                Arrays.asList("A. x", "B. y", "C. z", "D. w"), "A. x", "Borrador");
    }

    @Test
    void registraPreguntaValida() {
        assertTrue(service.registrarPregunta(preguntaValida()));
        assertEquals(1, service.obtenerTodas().size());
    }

    @Test
    void rechazaEnunciadoVacio() {
        Pregunta p = new Pregunta(2, "   ",
                Arrays.asList("A", "B", "C", "D"), "A", "Borrador");
        assertFalse(service.registrarPregunta(p));
        assertTrue(service.obtenerTodas().isEmpty());
    }

    @Test
    void rechazaOpcionesDistintasDeCuatro() {
        Pregunta p = new Pregunta(3, "Enunciado",
                Arrays.asList("A", "B", "C"), "A", "Borrador");
        assertFalse(service.registrarPregunta(p));
    }

    @Test
    void opcionesNulasSeInicializanVacias() {
        Pregunta p = new Pregunta(4, "Enunciado", null, "A", "Borrador");
        assertNotNull(p.getOpciones());
        assertFalse(service.registrarPregunta(p));
    }

    @Test
    void filtroOpcionesAceptaExactamenteCuatro() {
        assertTrue(new FiltroOpciones().procesar(preguntaValida()));
    }
}
