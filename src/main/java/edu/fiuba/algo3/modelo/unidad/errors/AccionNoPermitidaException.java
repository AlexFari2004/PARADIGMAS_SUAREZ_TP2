package edu.fiuba.algo3.modelo.unidad.errors;

import edu.fiuba.algo3.modelo.unidad.Accion;

public class AccionNoPermitidaException extends Exception {
    public AccionNoPermitidaException(Accion accion) {
        super("No quedan usos de la acción: " + accion);
    }
}
