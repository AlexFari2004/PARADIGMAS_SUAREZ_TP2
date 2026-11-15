package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.Deque;
import java.util.ArrayDeque;
import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;

abstract public class Mech {
    private Posicion posicion;
    private AccionesDisponibles acciones;
    private final Deque<Posicion> posicionesAnteriores = new ArrayDeque<>();

    protected Mech() {
        acciones.permitirAccion(Accion.MOVERSE, 1);
        acciones.permitirAccion(Accion.ATACAR, 1);
        acciones.permitirAccion(Accion.DESHACER, 1);
    }

    public void moverseA(Posicion destino, Tablero tablero) throws AccionNoPermitidaException {
        acciones.consumirAccion(Accion.MOVERSE);
        Posicion origen = posicion;
        tablero.mover(this, destino);
        posicionesAnteriores.push(origen);
    }

    public void atacar(Posicion objetivo, Tablero tablero) throws AccionNoPermitidaException {
        acciones.consumirAccion(Accion.ATACAR);
        ejecutarAtaque(objetivo, tablero);
        acciones.agotarAccion(Accion.MOVERSE);
        acciones.agotarAccion(Accion.DESHACER);
    }

    public void deshacerMovimiento(Tablero tablero) throws AccionNoPermitidaException {
        if (!posicionesAnteriores.isEmpty()) {
            acciones.consumirAccion(Accion.DESHACER);
            tablero.mover(this, posicionesAnteriores.pop());
            acciones.reestablecerAccion(Accion.MOVERSE);
        }
    }

    public void reestablecerEstado() {
        acciones.reestablecerAcciones();
        posicionesAnteriores.clear();
    }

    abstract public void ejecutarAtaque(Posicion objetivo, Tablero tablero);
}