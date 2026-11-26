package edu.fiuba.algo3.modelo.tablero;

public class Montania extends Casilla {
    @Override
    public boolean puedeSerOcupada() {
        return false;
    }

    @Override
    public boolean detieneAtaque() {
        return true;
    }

    @Override
    public void recibirDanio(int danio, Tablero tablero, Posicion posicion) {
        // obstaculo indestructible y no recibe daño
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.MONTANIA;
    }
}
