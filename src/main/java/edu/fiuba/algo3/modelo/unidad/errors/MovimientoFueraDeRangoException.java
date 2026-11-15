package edu.fiuba.algo3.modelo.unidad.errors;

public class MovimientoFueraDeRangoException extends RuntimeException {
    public MovimientoFueraDeRangoException(String mensaje) {
        super(mensaje);
    }

    public MovimientoFueraDeRangoException() {
        super();
    }
}