package edu.fiuba.algo3.modelo.errors.unidad;

import edu.fiuba.algo3.modelo.unidad.acciones.Accion;

public class AccionNoPermitidaException extends RuntimeException {
    public AccionNoPermitidaException(Accion accion) {
        super("No quedan usos de la acción: " + accion);
    }
}
