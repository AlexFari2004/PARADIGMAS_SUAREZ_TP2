package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.errores.unidad.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.errores.unidad.SinMovimientoParaDeshacerException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.mech.acciones.Accion;
import edu.fiuba.algo3.modelo.unidad.mech.acciones.AccionesDisponibles;
import edu.fiuba.algo3.modelo.unidad.movimiento.Movible;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Set;


abstract public class Mech extends Unidad {
    private final AccionesDisponibles acciones = new AccionesDisponibles();
    private final Deque<MovimientoRealizado> movimientosRealizados = new ArrayDeque<>();

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
        if (movimientosRealizados.isEmpty()) {
            throw new SinMovimientoParaDeshacerException();
        }
        MovimientoRealizado ultimo = movimientosRealizados.pop();
        this.movimiento.mover(this, ultimo.origen(), tablero);
        restaurarEstado(ultimo.estadoPrevio());
        acciones.reestablecerAccion(Accion.MOVERSE);
    }

    private void ejecutarMovimiento(Posicion posicionDestino, Tablero tablero) {
        MovimientoRealizado movimientoRealizado = new MovimientoRealizado(this.posicion, obtenerEstado());
        this.movimiento.mover(this, posicionDestino, tablero);
        movimientosRealizados.push(movimientoRealizado);
    }

    private void ejecutarAtaqueDesde(Posicion objetivo, Tablero tablero) {
        tablero.obtenerCasilla(this.posicion).validarAtaqueDesdeAca();
        ejecutarAtaque(objetivo, tablero);
    }

    abstract protected void ejecutarAtaque(Posicion objetivo, Tablero tablero);

    // METODOS PRINCIPALES

    public void atacar(Posicion objetivo, Tablero tablero) {
        acciones.ejecutar(Accion.ATACAR, () -> ejecutarAtaqueDesde(objetivo, tablero));
    }

    public void deshacerMovimiento(Tablero tablero) throws AccionNoPermitidaException {
        acciones.ejecutar(Accion.DESHACER, () -> ejecutarDeshacerMovimiento(tablero));
    }

    public void reestablecerEstado() {
        acciones.reestablecerAcciones();
        movimientosRealizados.clear();
    }

    public void mover(Posicion posicionDestino, Tablero tablero) {
        acciones.ejecutar(Accion.MOVERSE, () -> ejecutarMovimiento(posicionDestino, tablero));
    }

    @Override
    public void registrarEn(Tablero tablero) {
        tablero.registrarMech(this);
    }

    @Override
    public void hundirse() {
    }
}
