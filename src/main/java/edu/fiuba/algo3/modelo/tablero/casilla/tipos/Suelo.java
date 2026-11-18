package edu.fiuba.algo3.modelo.tablero.casilla.tipos;

import edu.fiuba.algo3.modelo.tablero.casilla.Casilla;
import edu.fiuba.algo3.modelo.tablero.casilla.TipoCasilla;

public class Suelo extends Casilla {

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.SUELO;
    }
}
