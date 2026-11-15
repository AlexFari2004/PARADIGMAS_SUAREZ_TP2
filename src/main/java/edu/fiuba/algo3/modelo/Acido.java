package edu.fiuba.algo3.modelo;

public class Acido extends Casilla {

    public Acido(int fila, int columna, TipoCasilla tipo) {
        super(fila, columna, tipo);
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.ACIDO;
    }
}
