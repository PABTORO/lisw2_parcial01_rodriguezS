package co.edu.unicauca.saberpro.repository;
import co.edu.unicauca.saberpro.model.Pregunta;
import java.util.ArrayList;
import java.util.List;
public class PreguntaRepositoryImpl implements IPreguntaRepository {
    private List<Pregunta> bancoPreguntas = new ArrayList<>();
    @Override
    public boolean guardar(Pregunta pregunta) {
        return bancoPreguntas.add(pregunta);
    }
    @Override
    public List<Pregunta> listar() {
        return bancoPreguntas;
    }
    @Override
    public Pregunta buscarPorId(int id) {
        return bancoPreguntas.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);
    }
}
