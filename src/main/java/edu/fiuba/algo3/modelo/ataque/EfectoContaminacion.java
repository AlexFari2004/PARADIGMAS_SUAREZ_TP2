package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoContaminacion implements EfectoAtaque {
    @Override
    public void aplicarEfecto(Direccion direccion, Posicion impacto, Tablero tablero) {
        tablero.obtenerCasilla(impacto).getUnidad().contaminar();
    }
}
