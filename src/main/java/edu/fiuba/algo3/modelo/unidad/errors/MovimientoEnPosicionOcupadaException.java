package edu.fiuba.algo3.modelo.unidad.errors;

public class MovimientoEnPosicionOcupadaException extends RuntimeException {
    public MovimientoEnPosicionOcupadaException(String mensaje) {
        super(mensaje);
    }

    public MovimientoEnPosicionOcupadaException() {
        super();
    }
}