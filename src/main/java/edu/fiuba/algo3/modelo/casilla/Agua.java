package edu.fiuba.algo3.modelo.casilla;

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
