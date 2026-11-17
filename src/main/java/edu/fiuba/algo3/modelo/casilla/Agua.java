package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Agua extends Casilla {

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.AGUA;
    }
    @Override
    protected void aplicarEfecto(Unidad unidad){
        unidad.caerAlAgua();
    }
}
