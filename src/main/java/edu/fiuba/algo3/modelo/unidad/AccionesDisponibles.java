package edu.fiuba.algo3.modelo.unidad;

import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;

public class AccionesDisponibles {
    private final Map<Accion, Integer> permitidas = new EnumMap<>(Accion.class);
    private final Map<Accion, Integer> restantes  = new EnumMap<>(Accion.class);
    private final Map<Accion, Set<Accion>> bloqueos = new EnumMap<>(Accion.class);

    public AccionesDisponibles() {}

    public void permitirAccion(Accion accion, int cantidad) {
        permitidas.put(accion, cantidad);
        restantes.put(accion, cantidad);
    }

    public void bloquear(Accion causante, Collection<Accion> bloqueadas ) {
        bloqueos.put(causante, EnumSet.copyOf(bloqueadas));
    }

    public void ejecutar(Accion accion, Runnable operacion) throws AccionNoPermitidaException {
        validar(accion);
        operacion.run();
        consumirAccion(accion);
    }

    private void consumirAccion(Accion accion) {
        restantes.merge(accion, -1, Integer::sum);
        bloqueos.getOrDefault(accion, Set.of())
                .forEach(bloqueada -> restantes.put(bloqueada, 0));
    }

    private void validar(Accion accion) throws AccionNoPermitidaException {
        int accionesRestantes = restantes.getOrDefault(accion, 0);
        if (accionesRestantes <= 0) {
            throw new AccionNoPermitidaException(accion);
        }
    }

    public void reestablecerAccion(Accion accion) {
        restantes.put(accion, permitidas.get(accion));
    }

    public void reestablecerAcciones() {
        restantes.putAll(permitidas);
    }
}