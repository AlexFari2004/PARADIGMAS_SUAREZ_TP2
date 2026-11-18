package edu.fiuba.algo3.modelo.tablero.casilla.tipos;

import edu.fiuba.algo3.modelo.tablero.casilla.Casilla;
import edu.fiuba.algo3.modelo.tablero.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Acido extends Casilla {

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.ACIDO;
    }

    @Override
    protected void aplicarEfecto(Unidad unidad) {
        unidad.pisarAcido();
    }

}
