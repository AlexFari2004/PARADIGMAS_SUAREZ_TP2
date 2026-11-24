package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.Unidad;

public interface Movible {

    void mover(Unidad unidad, Posicion nuevaPosicion, Tablero tablero);

    void sumergir(Unidad unidad);

    void corroer(Unidad unidad);
}
