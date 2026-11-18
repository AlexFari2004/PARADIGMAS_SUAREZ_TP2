package edu.fiuba.algo3.modelo.casilla;

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
