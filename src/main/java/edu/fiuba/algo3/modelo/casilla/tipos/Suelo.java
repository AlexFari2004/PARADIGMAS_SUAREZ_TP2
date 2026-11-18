package edu.fiuba.algo3.modelo.casilla.tipos;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.TipoCasilla;

public class Suelo extends Casilla {

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.SUELO;
    }
}
