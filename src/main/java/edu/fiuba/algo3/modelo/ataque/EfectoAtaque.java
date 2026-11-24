package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EfectoAtaque {
    void aplicarEfecto(Posicion atacante, Posicion impacto, Tablero tablero);
}
