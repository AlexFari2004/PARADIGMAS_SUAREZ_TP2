package edu.fiuba.algo3.modelo.errores;

public class LimiteDeAccionesAlcanzadaException extends RuntimeException {
    public LimiteDeAccionesAlcanzadaException(String mensaje) {
        super(mensaje);
    }

    public LimiteDeAccionesAlcanzadaException() {
        super();
    }
}