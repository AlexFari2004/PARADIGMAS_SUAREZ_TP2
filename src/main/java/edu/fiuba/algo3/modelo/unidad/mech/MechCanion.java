package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.arma.Canion;
import edu.fiuba.algo3.modelo.unidad.movimientos.MovimientoMech;

public class MechCanion extends Mech {
    private final Canion canion;

    public MechCanion(Canion canion) {
        super(3, new MovimientoMech());
        this.canion = canion;
    }

    @Override
    protected void ejecutarAtaque(Posicion destino, Tablero tablero) {
        canion.atacar(this.posicion, destino, tablero);
    }
}