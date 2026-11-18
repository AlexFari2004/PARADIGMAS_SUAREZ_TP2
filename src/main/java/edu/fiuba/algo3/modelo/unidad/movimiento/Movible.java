package edu.fiuba.algo3.modelo.unidad.movimiento;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public interface Movible {

    void mover(Unidad unidad, Posicion nuevaPosicion, Tablero tablero);

}
