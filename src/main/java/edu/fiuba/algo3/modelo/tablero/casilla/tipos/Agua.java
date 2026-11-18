package edu.fiuba.algo3.modelo.tablero.casilla.tipos;

import edu.fiuba.algo3.modelo.errores.unidad.AtaqueDesdeAguaException;
import edu.fiuba.algo3.modelo.tablero.casilla.Casilla;
import edu.fiuba.algo3.modelo.tablero.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.unidad.Unidad;

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
