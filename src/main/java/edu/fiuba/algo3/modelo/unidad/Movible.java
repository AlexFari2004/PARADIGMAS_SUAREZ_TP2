package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

public interface Movible {

    void mover(Unidad unidad, Posicion nuevaPosicion, Tablero tablero);

}
