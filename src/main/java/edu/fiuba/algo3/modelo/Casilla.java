package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.errors.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.errors.MovimientoEnPosicionOcupadaException;

public abstract class Casilla {
    private Mech ocupante;
    public abstract TipoCasilla getTipo();

    public final void ocupar(Mech mech) throws CasillaNoTransitableException {
        if (!esTransitablePara(mech)) {
            throw new CasillaNoTransitableException();
        }
        if (ocupante != null) {
            throw new MovimientoEnPosicionOcupadaException();
        }
        this.ocupante = mech;
    }

    protected boolean esTransitablePara(Mech mech) {
        return mech.puedePisar(getTipo());
    }

    public void liberar() {
        this.ocupante = null;
    }
}