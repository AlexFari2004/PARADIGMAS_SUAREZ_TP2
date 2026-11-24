package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;

import java.util.EnumSet;

public class MovimientoVekTerrestre extends MovimientoVek{
    public MovimientoVekTerrestre(int rango) {
        super(
                rango,
                EnumSet.of(TipoCasilla.SUELO, TipoCasilla.AGUA, TipoCasilla.ACIDO),
                EnumSet.of(TipoCasilla.SUELO)
        );
    }

    @Override
    protected boolean puedeLlegar(Unidad unidad, Posicion destino, Tablero tablero) {
        return existeCaminoLibre(unidad, destino, tablero);
    }

    @Override
    public void sumergir(Unidad unidad) {
        unidad.destruir();
    }

    @Override
    public void corroer(Unidad unidad) {
        unidad.contaminar();
    }
}
