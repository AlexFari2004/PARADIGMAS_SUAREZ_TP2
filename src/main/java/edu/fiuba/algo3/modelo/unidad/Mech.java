package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.AccionesDisponibles;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.unidad.Accion;
import java.util.Deque;
import java.util.ArrayDeque;
import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;

abstract public class Mech {
    private Posicion posicion;
    private final AccionesDisponibles acciones;
    private final Deque<Posicion> posicionesAnteriores = new ArrayDeque<>();

    protected Mech(AccionesDisponibles acciones) {
        this.acciones = acciones;
    }

    public void moverseA(Posicion destino, Tablero tablero) {
        acciones.consumir(Accion.MOVERSE);
        Posicion origen = posicion;
        tablero.mover(this, destino);
        posicionesAnteriores.push(origen);
    }

    public void atacar(Posicion objetivo, Tablero tablero) {
        acciones.consumir(Accion.ATACAR);
        ejecutarAtaque(objetivo, tablero);
        acciones.agotar(Accion.MOVERSE);
        acciones.agotar(Accion.DESHACER);
    }

    public void deshacerMovimiento(Tablero tablero) {
        if (posicionesAnteriores.isEmpty()) {
            throw new AccionNoPermitidaException(Accion.DESHACER);
        }
        acciones.consumir(Accion.DESHACER);
        tablero.mover(this, posicionesAnteriores.pop());
        acciones.devolver(Accion.MOVERSE);
    }

    public void iniciarTurno() {
        acciones.reiniciar();
        posicionesAnteriores.clear();
    }

    abstract public void ejecutarAtaque(Posicion objetivo, Tablero tablero);
}