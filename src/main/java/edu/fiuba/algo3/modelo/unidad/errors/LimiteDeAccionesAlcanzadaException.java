package edu.fiuba.algo3.modelo.unidad.errors;

public class LimiteDeAccionesAlcanzadaException extends RuntimeException {
    public LimiteDeAccionesAlcanzadaException(String mensaje) {
        super(mensaje);
    }

    public LimiteDeAccionesAlcanzadaException() {
        super();
    }
}