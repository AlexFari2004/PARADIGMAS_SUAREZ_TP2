package edu.fiuba.algo3.modelo.errores;

public class MovimientoFueraDeRangoException extends RuntimeException {
    public MovimientoFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}
