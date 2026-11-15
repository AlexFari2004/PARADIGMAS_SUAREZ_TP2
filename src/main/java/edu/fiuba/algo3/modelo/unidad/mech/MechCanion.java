package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.mock.TableroStub;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.mech.arma.Canion;

public class MechCanion extends Unidad {
    private final Canion canion;

    public MechCanion(Canion canion) {
        this.canion = canion;
        this.vida = 3;
    }

    public void atacar(Posicion destino, TableroStub tablero) {
        canion.atacar(this.posicion, destino, tablero);
    }
}