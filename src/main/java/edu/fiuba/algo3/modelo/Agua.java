package edu.fiuba.algo3.modelo;

public class Agua extends Casilla {

    public Agua(int fila, int columna, TipoCasilla tipo) {
        super(fila, columna, tipo);
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.AGUA;
    }
}
