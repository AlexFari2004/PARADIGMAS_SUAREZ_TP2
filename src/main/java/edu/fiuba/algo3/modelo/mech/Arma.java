package edu.fiuba.algo3.modelo.mech;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface Arma {
    void atacar(Posicion atacante, Posicion objetivo, Tablero tablero);
}
