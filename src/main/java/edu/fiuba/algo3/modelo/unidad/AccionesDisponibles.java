package edu.fiuba.algo3.modelo.unidad;

import java.util.EnumMap;
import java.util.Map;

import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;

public class AccionesDisponibles {
    private final Map<Accion, Integer> permitidas = new EnumMap<>(Accion.class);
    private final Map<Accion, Integer> restantes  = new EnumMap<>(Accion.class);

    public AccionesDisponibles() {}

    public void permitirAccion(Accion accion, int cantidad) {}

    public void consumirAccion(Accion accion) throws AccionNoPermitidaException {
        validar(accion);
        restantes.put(accion, restantes.get(accion) - 1);
    }

    private void validar(Accion accion) throws AccionNoPermitidaException {
        if (restantes.get(accion) <= 0) {
            throw new AccionNoPermitidaException(accion);
        }
    }

    public void agotarAccion(Accion accion) throws AccionNoPermitidaException{
        validar(accion);
        restantes.put(accion, 0);
    }

    public int accionesRestantes(Accion accion) throws AccionNoPermitidaException {
        validar(accion);
        return restantes.getOrDefault(accion, 0);
    }

    public void reestablecerAccion(Accion accion) throws AccionNoPermitidaException {
        validar(accion);
        int cantAccionOriginal = permitidas.get(accion);
        restantes.put(accion, cantAccionOriginal);
    }

    public void reestablecerAcciones() {
        restantes.putAll(permitidas);
    }
}