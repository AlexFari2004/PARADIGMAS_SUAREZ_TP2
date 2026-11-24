package edu.fiuba.algo3.modelo.mech;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.MovimientoMech;

public class MechCombate extends Mech {
    private final Punio punio;

    public MechCombate(Punio punio) {
        super(3, new MovimientoMech());
        this.punio = punio;
    }

    @Override
    protected void ejecutarAtaque(Posicion destino, Tablero tablero) {
        punio.atacar(posicion, destino, tablero);
    }
}