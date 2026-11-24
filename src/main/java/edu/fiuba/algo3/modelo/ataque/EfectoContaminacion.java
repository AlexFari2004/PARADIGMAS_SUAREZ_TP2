package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoContaminacion implements EfectoAtaque {
    @Override
    public void aplicarEfecto(Posicion atacante, Posicion impacto, Tablero tablero) {
        tablero.obtenerCasilla(impacto).getOcupante().contaminar();
    }
}
