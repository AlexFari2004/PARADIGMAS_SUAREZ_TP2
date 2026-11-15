package edu.fiuba.algo3.modelo;

public class Edificio extends Casilla {

    public Edificio(int fila, int columna, TipoCasilla tipo) {
        super(fila, columna, tipo);
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.EDIFICIO;
    }
}
