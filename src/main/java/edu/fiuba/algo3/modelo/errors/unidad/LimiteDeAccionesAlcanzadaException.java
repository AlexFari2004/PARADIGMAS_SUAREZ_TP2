package edu.fiuba.algo3.modelo.errors.unidad;

public class LimiteDeAccionesAlcanzadaException extends RuntimeException {
    public LimiteDeAccionesAlcanzadaException(String mensaje) {
        super(mensaje);
    }

    public LimiteDeAccionesAlcanzadaException() {
        super();
    }
}