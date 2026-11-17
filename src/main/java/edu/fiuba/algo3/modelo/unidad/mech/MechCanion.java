package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.arma.Canion;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class MechCanion extends Mech {
    private final Canion canion;

    public MechCanion(Canion canion) {
        this.canion = canion;
        this.vida = 3;
    }

    public void atacar(Posicion destino, Tablero tablero) {
        canion.atacar(this.posicion, destino, tablero);
    }
}