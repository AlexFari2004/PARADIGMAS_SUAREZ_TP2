package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueAdyacente extends AtaqueBase {
    @Override
    protected void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero) {
        if (!tablero.estaDentro(objetivo) || atacante.distanciaA(objetivo) != 1) {
            throw new IllegalArgumentException("El objetivo está fuera del alcance.");
        }
    }

    @Override
    public Posicion calcularImpacto(Posicion atacante, Direccion direccion, Tablero tablero) {
        return direccion.desde(atacante);
    }
}
