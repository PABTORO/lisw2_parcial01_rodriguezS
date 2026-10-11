package co.edu.unicauca.saberpro.model;
import java.util.List;
import java.util.ArrayList;
public class Pregunta {
    private int id;
    private String enunciado;
    private List<String> opciones; // Debe contener 4 opciones
    private String respuestaCorrecta;
    private String estado; // "Borrador", "Publicada"
    public Pregunta(int id, String enunciado, List<String> opciones, String
    respuestaCorrecta, String estado) {
        this.id = id;
        this.enunciado = enunciado;
        // ERROR INTENCIONAL 1: Si opciones llega nulo o vacío, debe garantizarse la
        //inicialización
        this.opciones = (opciones == null || opciones.isEmpty())
            ? new ArrayList<>() : opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.estado = estado;
    }
    public int getId() { return id; }
    public String getEnunciado() { return enunciado; }
    public List<String> getOpciones() { return opciones; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
