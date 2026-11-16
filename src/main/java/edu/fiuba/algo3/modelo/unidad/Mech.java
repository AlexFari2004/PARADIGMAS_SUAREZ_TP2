package edu.fiuba.algo3.modelo.unidad;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.errors.AccionNoPermitidaException;


abstract public class Mech extends Unidad {
    private final AccionesDisponibles acciones = new AccionesDisponibles();;
    private final Deque<Posicion> posicionesAnteriores = new ArrayDeque<>();

    public Mech(int vida, Movible movimiento) {
        super(vida, movimiento);

        acciones.permitirAccion(Accion.MOVERSE, 1);
        acciones.permitirAccion(Accion.ATACAR, 1);
        acciones.permitirAccion(Accion.DESHACER, 1);

        Set<Accion> accionesAgotablesPorAtaque = EnumSet.of(Accion.MOVERSE, Accion.DESHACER);
        acciones.bloquear(Accion.ATACAR, accionesAgotablesPorAtaque);
    }

    // METODOS EJECUTAR

    private void ejecutarDeshacerMovimiento(Tablero tablero) {
        ejecutarMovimiento(posicionesAnteriores.pop(), tablero);
        acciones.reestablecerAccion(Accion.MOVERSE);
    }

    private void ejecutarMovimiento(Posicion posicionDestino, Tablero tablero) {
        Posicion origen = this.posicion;
        this.movimiento.mover(this, posicionDestino, tablero);
        posicionesAnteriores.push(origen);
    }

    abstract protected void ejecutarAtaque(Posicion objetivo, Tablero tablero);

    // METODOS PRINCIPALES

    public void atacar(Posicion objetivo, Tablero tablero) {
        acciones.ejecutar(Accion.ATACAR, () -> ejecutarAtaque(objetivo, tablero));
    }

    public void deshacerMovimiento(Tablero tablero) throws AccionNoPermitidaException {
        if (!posicionesAnteriores.isEmpty()) {
            acciones.ejecutar(Accion.DESHACER, () -> ejecutarDeshacerMovimiento(tablero));
        }
    }

    public void reestablecerEstado() {
        acciones.reestablecerAcciones();
        posicionesAnteriores.clear();
    }

    public void mover(Posicion posicionDestino, Tablero tablero){
        acciones.ejecutar(Accion.MOVERSE, () -> ejecutarMovimiento(posicionDestino, tablero));
    }
}
