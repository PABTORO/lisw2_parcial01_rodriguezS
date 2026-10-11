package co.edu.unicauca.saberpro.pipeline;

import co.edu.unicauca.saberpro.model.Pregunta;

/*Filtro HU03 - RF-11: verifica que la respuesta correcta coincida
exactamente con una de las opciones de la pregunta*/
public class FiltroRespuestaCorrecta implements IFiltroPregunta {
    @Override
    public boolean procesar(Pregunta pregunta) {
        if (pregunta == null
                || pregunta.getRespuestaCorrecta() == null
                || pregunta.getOpciones() == null) {
            return false;
        }
        return pregunta.getOpciones().contains(pregunta.getRespuestaCorrecta());
    }
}
