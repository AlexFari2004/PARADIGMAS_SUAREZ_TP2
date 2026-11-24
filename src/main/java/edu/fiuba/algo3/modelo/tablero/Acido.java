package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Unidad;

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
