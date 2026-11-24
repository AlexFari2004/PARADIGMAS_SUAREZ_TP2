package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.errores.AtaqueDesdeAguaException;
import edu.fiuba.algo3.modelo.Unidad;

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
