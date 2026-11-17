package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.MovimientoMech;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.Mech;

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