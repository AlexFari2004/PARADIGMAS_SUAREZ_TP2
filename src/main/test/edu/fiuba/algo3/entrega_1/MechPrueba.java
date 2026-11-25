package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.movimiento.MovimientoMech;

public class MechPrueba extends Mech {
    public MechPrueba(int vida) {
        super(vida, new MovimientoMech());
    }

    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        // No necesario de implementar para el proposito de los test
    }
}