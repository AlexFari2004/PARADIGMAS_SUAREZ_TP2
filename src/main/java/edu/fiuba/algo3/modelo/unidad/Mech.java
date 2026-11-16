package edu.fiuba.algo3.modelo.unidad;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.TipoCasilla;
import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;

/*
abstract public class Mech {
    private Posicion posicion;
    private final int rangoMovimiento;
    private final AccionesDisponibles acciones = new AccionesDisponibles();;
    private final Deque<Posicion> posicionesAnteriores = new ArrayDeque<>();
    private final Set<TipoCasilla> transitables;

    protected Mech() {
        this.rangoMovimiento = 1;

        acciones.permitirAccion(Accion.MOVERSE, 1);
        acciones.permitirAccion(Accion.ATACAR, 1);
        acciones.permitirAccion(Accion.DESHACER, 1);

        transitables = EnumSet.of(TipoCasilla.SUELO, TipoCasilla.AGUA, TipoCasilla.ACIDO);

        Set<Accion> accionesAgotablesPorAtaque = EnumSet.of(Accion.MOVERSE, Accion.DESHACER);
        acciones.bloquear(Accion.ATACAR, accionesAgotablesPorAtaque);
    }

    // GETTERS

    public Posicion obtenerPosicion() {
        return this.posicion;
    }

    public int obtenerRangoMovimiento() {
        return this.rangoMovimiento;
    }

    public void establecerPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
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
    public void moverseA(Posicion destino, Tablero tablero) {
        acciones.ejecutar(Accion.MOVERSE, () -> ejecutarMovimiento(destino, tablero));
    }

    public void atacar(Posicion objetivo, Tablero tablero) {
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

    public boolean puedePisar(TipoCasilla tipoCasilla) {
        return transitables.contains(tipoCasilla);
    }
}
*/
