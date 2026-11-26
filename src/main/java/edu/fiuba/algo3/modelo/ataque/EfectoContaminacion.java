package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoContaminacion implements EfectoAtaque {
    @Override
    public void aplicarEfecto(Direccion direccion, Posicion posicion, Tablero tablero) {
        Casilla casilla = tablero.obtenerCasilla(posicion);
        if (casilla.estaOcupada()) {
            casilla.getUnidad().contaminar();
        }
    }
}
