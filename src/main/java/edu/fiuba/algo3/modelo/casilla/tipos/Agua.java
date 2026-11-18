package edu.fiuba.algo3.modelo.casilla.tipos;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.errors.unidad.AtaqueDesdeAguaException;

public class Agua extends Casilla {

    @Override
    public TipoCasilla getTipo() {
        return TipoCasilla.AGUA;
    }

    @Override
    protected void aplicarEfecto(Unidad unidad) {
        unidad.caerAlAgua();
    }

    @Override
    public void validarAtaqueDesdeAca() {
        throw new AtaqueDesdeAguaException();
    }
}
