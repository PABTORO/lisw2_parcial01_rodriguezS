package co.edu.unicauca.saberpro.pipeline;
import co.edu.unicauca.saberpro.model.Pregunta;
public class FiltroOpciones implements IFiltroPregunta {
    @Override
    public boolean procesar(Pregunta pregunta) {
        if (pregunta == null || pregunta.getOpciones() == null) {
            return false;
        }
        // ERROR INTENCIONAL 2: Lógica invertida en la validación de opciones (HU03)
        // Debe ser exactamente 4 opciones para retornar true.
        return pregunta.getOpciones().size() == 4;
    }
}

