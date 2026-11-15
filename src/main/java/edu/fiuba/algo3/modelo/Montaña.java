package edu.fiuba.algo3.modelo;

public class Montaña extends Casilla {

    public Montaña(int fila, int columna, TipoCasilla tipo) {
        super(fila, columna, tipo);
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.MONTAÑA;
    }
}
