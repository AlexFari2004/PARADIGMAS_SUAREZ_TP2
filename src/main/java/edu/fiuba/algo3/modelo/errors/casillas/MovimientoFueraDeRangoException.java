package edu.fiuba.algo3.modelo.errors.casillas;

public class MovimientoFueraDeRangoException extends RuntimeException {
    public MovimientoFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}
