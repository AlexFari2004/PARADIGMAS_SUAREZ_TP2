package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.movimiento.MovimientoMech;

public class MechCombate extends Mech {
    private final Puno puno;

    public MechCombate(Puno puno) {
        super(3, new MovimientoMech());
        this.puno = puno;
    }

    @Override
    protected void ejecutarAtaque(Posicion destino, Tablero tablero) {
        puno.atacar(posicion, destino, tablero);
    }
}