package edu.fiuba.algo3.modelo;

public class Suelo extends Casilla {

    public Suelo(int fila, int columna, TipoCasilla tipo) {
        super(fila, columna, tipo);
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.SUELO;
    }
}
