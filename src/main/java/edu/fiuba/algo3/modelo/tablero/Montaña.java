package edu.fiuba.algo3.modelo.tablero;

public class Montaña extends Casilla {
    @Override
    public boolean puedeSerOcupada() {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public void recibirDanio(int danio, Tablero tablero, Posicion posicion) {
        // Regla 3.5.2: la montaña es indestructible y no recibe daño
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.MONTAÑA;
    }
}
