package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface Ataque {
    void generarAtaque(Posicion atacante, Posicion objetivo, Tablero tablero,
                       int danio, EfectoAtaque efecto);

    void generarAtaqueEnDireccion(Posicion atacante, Direccion direccion, Tablero tablero,
                                  int danio, EfectoAtaque efecto);

    Posicion calcularImpacto(Posicion atacante, Direccion direccion, Tablero tablero);

    Posicion calcularImpactoDesdeSimulacion(Posicion origen, Posicion posicionAIgnorar,
                                            Direccion direccion, Tablero tablero);
}



