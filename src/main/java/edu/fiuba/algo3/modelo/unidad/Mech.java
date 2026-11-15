package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;
import java.util.ArrayDeque;
import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;

abstract public class Mech {
    private Posicion posicion;
    private final int rangoMovimiento;
    private final AccionesDisponibles acciones;
    private final Deque<Posicion> posicionesAnteriores = new ArrayDeque<>();

    protected Mech() {
        this.rangoMovimiento = 1;

        this.acciones = new AccionesDisponibles();
        acciones.permitirAccion(Accion.MOVERSE, 1);
        acciones.permitirAccion(Accion.ATACAR, 1);
        acciones.permitirAccion(Accion.DESHACER, 1);

        Set<Accion> accionesAgotablesPorAtaque = EnumSet.of(Accion.MOVERSE, Accion.DESHACER);
        acciones.bloquear(Accion.ATACAR, accionesAgotablesPorAtaque);
    }

    // GETTERS & SETTERS

    public Posicion obtenerPosicion() {
        return this.posicion;
    }

    public void establecerPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }

    public int obtenerRangoMovimiento() {
        return this.rangoMovimiento;
    }

    // METODOS EJECUTAR

    private void ejecutarMovimiento(Posicion destino, Tablero tablero) {
        Posicion origen = posicion;
        tablero.mover(this, destino);
        posicionesAnteriores.push(origen);
    }

    private void ejecutarDeshacerMovimiento(Tablero tablero) {
        tablero.mover(this, posicionesAnteriores.pop());
    }

    abstract protected void ejecutarAtaque(Posicion objetivo, Tablero tablero);

    // METODOS PRINCIPALES
    public void moverseA(Posicion destino, Tablero tablero) throws AccionNoPermitidaException {
        acciones.ejecutar(Accion.MOVERSE, () -> ejecutarMovimiento(destino, tablero));
    }

    public void atacar(Posicion objetivo, Tablero tablero) throws AccionNoPermitidaException {
        acciones.ejecutar(Accion.ATACAR, () -> ejecutarAtaque(objetivo, tablero));
    }

    public void deshacerMovimiento(Tablero tablero) throws AccionNoPermitidaException {
        if (!posicionesAnteriores.isEmpty()) {
            acciones.ejecutar(Accion.DESHACER, () -> ejecutarDeshacerMovimiento(tablero));
            acciones.reestablecerAccion(Accion.MOVERSE);
        }
    }

    public void reestablecerEstado() {
        acciones.reestablecerAcciones();
        posicionesAnteriores.clear();
    }
}