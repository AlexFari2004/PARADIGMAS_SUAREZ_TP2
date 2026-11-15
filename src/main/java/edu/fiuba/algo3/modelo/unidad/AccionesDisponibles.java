package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidad.Accion;

import java.util.EnumMap;
import java.util.Map;

public class AccionesDisponibles {
    private final Map<Accion, Integer> permitidas = new EnumMap<>(Accion.class);
    private final Map<Accion, Integer> restantes  = new EnumMap<>(Accion.class);

    public AccionesDisponibles permitir(Accion accion, int cantidad) {
    }

    public void consumirAccion(Accion accion) {
    }

    public void devolverAccion(Accion accion) {
    }

    public void agotarAccion(Accion accion) {
    }

    public int accionesRestantes(Accion accion) {
    }

    public void reiniciarAcciones() {
        restantes.putAll(permitidas);
    }
}