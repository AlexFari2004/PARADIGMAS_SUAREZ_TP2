package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface EfectoAtaque {
    void aplicarEfecto(Direccion direccion, Posicion impacto, Tablero tablero);
}
