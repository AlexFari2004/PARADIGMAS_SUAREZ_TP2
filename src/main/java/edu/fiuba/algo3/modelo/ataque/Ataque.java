package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface Ataque {
    void generarAtaque(Posicion atacante, Posicion objetivo, Tablero tablero,
                       int danio, EfectoAtaque efecto);
}
