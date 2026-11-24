package edu.fiuba.algo3.modelo.errores;

import edu.fiuba.algo3.modelo.mech.Accion;

public class AccionNoPermitidaException extends RuntimeException {
    public AccionNoPermitidaException(Accion accion) {
        super("No quedan usos de la acción: " + accion);
    }
}
