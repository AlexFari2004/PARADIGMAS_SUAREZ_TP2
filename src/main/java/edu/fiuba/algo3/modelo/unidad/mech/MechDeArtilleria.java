package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.mock.TableroStub;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.arma.Artilleria;
import edu.fiuba.algo3.modelo.unidad.Mech;


public class MechDeArtilleria extends Unidad {
    private final Artilleria arma;

    public MechDeArtilleria(Artilleria arma) {
        this.arma = arma;
        this.vida = 2; // El mech de artillería tiene 2 de vida[cite: 6]
    }

    public void atacar(Posicion destino, Tablero tablero) {
        arma.atacar(this.posicion, destino, tablero);
    }
}