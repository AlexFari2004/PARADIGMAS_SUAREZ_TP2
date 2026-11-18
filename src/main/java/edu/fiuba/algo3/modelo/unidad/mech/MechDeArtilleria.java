package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.mech.arma.Artilleria;
import edu.fiuba.algo3.modelo.unidad.movimiento.MovimientoMech;

public class MechDeArtilleria extends Mech {
    private final Artilleria arma;

    public MechDeArtilleria(Artilleria arma) {
        super(2, new MovimientoMech());
        this.arma = arma;
    }

    @Override
    protected void ejecutarAtaque(Posicion destino, Tablero tablero) {
        arma.atacar(this.posicion, destino, tablero);
    }
}