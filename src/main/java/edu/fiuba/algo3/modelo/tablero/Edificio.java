package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.ataque.EfectoAtaque;

public class Edificio extends Casilla {
    private static final int DANIO_RED_POR_DESTRUCCION = 1;


    @Override
    public void recibirDanio(int danio, Tablero tablero, Posicion posicion) {
        if (danio > 0) {
            tablero.consumirRedElectrica(DANIO_RED_POR_DESTRUCCION);
            tablero.reemplazarCasilla(posicion, TipoCasilla.SUELO.crearCasilla());
        }

    }

    @Override
    public boolean puedeSerOcupada() {
        return false;
    }

    @Override
    public boolean detieneDisparo() {
        return true;
    }

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.EDIFICIO;
    }
}
