package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.errores.MovimientoFueraDeRangoException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.Unidad;

import java.util.EnumSet;

public class MovimientoAvispon extends MovimientoVek {

    public MovimientoAvispon(int rango) {
        super(
                rango,
                EnumSet.of(TipoCasilla.SUELO, TipoCasilla.AGUA, TipoCasilla.ACIDO),
                EnumSet.of(TipoCasilla.SUELO, TipoCasilla.AGUA, TipoCasilla.ACIDO)
        );
    }

    @Override
    protected boolean puedeLlegar(Unidad unidad, Posicion destino, Tablero tablero) {
        return unidad.obtenerPosicion().distanciaA(destino) <= this.rangoMovimiento;
    }

    @Override
    public void sumergir(Unidad unidad) {
        // no afecta
    }

    @Override
    public void corroer(Unidad unidad) {
        // no afecta
    }

}
