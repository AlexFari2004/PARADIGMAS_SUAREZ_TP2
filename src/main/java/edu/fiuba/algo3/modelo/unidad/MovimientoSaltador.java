package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.errors.MovimientoFueraDeRangoException;

import java.util.EnumSet;

public class MovimientoSaltador extends MovimientoVek{
    public MovimientoSaltador() {
        super(3, EnumSet.of(TipoCasilla.SUELO, TipoCasilla.ACIDO));
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
