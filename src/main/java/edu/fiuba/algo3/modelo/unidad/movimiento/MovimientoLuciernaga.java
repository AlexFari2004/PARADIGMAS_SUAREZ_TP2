package edu.fiuba.algo3.modelo.unidad.movimiento;

import edu.fiuba.algo3.modelo.errores.unidad.MovimientoFueraDeRangoException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.unidad.Unidad;

import java.util.EnumSet;

public class MovimientoLuciernaga extends MovimientoVek {
    public MovimientoLuciernaga() {
        super(2, EnumSet.of(TipoCasilla.SUELO, TipoCasilla.ACIDO));
    }

    @Override
    protected boolean puedeLlegar(Unidad unidad, Posicion destino, Tablero tablero) {
        return existeCaminoLibre(unidad, destino, tablero);
    }

    @Override
    public void mover(Unidad unidad, Posicion destino, Tablero tablero) {
        validadDestino(destino, tablero);
        if (!puedeLlegar(unidad, destino, tablero))
            throw new MovimientoFueraDeRangoException("Movimiento fuera del rango de la unidad");
        desplazar(unidad, destino, tablero);
    }

}
