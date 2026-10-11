package co.edu.unicauca.saberpro.service;
import co.edu.unicauca.saberpro.model.Pregunta;
import co.edu.unicauca.saberpro.pipeline.ValidadorPipeline;
import co.edu.unicauca.saberpro.repository.IPreguntaRepository;
import java.util.List;
public class PreguntaServiceImpl implements IPreguntaService {
    private final IPreguntaRepository repository;
    private final ValidadorPipeline pipeline;
    public PreguntaServiceImpl(IPreguntaRepository repository, ValidadorPipeline pipeline) {
        this.repository = repository;
        this.pipeline = pipeline;
    }
    // ERROR INTENCIONAL 3: Firma del método no coincide con la interfaz IPreguntaService
    // La interfaz define 'registrarPregunta(Pregunta p)' pero aquí escribieron
    //'guardarPregunta'
    // CORREGIDO (Punto 1 - Error 3, línea 18): el método se llamaba guardarPregunta; 
    // ahora es registrarPregunta (con @Override) como exige IPreguntaService.
    @Override
    public boolean registrarPregunta(Pregunta pregunta) {
        if (pipeline.ejecutar(pregunta)) {
            return repository.guardar(pregunta);
        }
        return false;
    }
    
    @Override
    public List<Pregunta> obtenerTodas() {
        return repository.listar();
    }
}
