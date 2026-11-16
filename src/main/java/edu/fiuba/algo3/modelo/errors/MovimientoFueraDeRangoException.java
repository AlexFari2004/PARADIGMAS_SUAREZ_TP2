package edu.fiuba.algo3.modelo.errors;

public class MovimientoFueraDeRangoException extends RuntimeException {
    public MovimientoFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}
