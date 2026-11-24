package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueAdyacente extends AtaqueBase {
    @Override
    protected void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero) {
        if (!tablero.estaDentro(objetivo) || atacante.distanciaA(objetivo) != 1) {
            throw new IllegalArgumentException("El objetivo fuera del alcance.");
        }
    }

    @Override
    protected Posicion calcularImpacto(Posicion atacante, Posicion objetivo, Tablero tablero) {
        return objetivo;
    }

}
