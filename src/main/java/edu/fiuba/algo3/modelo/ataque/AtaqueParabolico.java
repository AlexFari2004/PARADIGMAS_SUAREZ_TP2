package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueParabolico extends AtaqueBase {
    private final int distanciaMinima;

    public AtaqueParabolico(int distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }

    @Override
    protected void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero) {
        if (!tablero.estaDentro(objetivo)
                || !atacante.estaAlineadaCon(objetivo)
                || atacante.distanciaA(objetivo) < distanciaMinima) {
            throw new IllegalArgumentException("Fuera de alcance");
        }
    }

    @Override
    protected Posicion calcularImpacto(Posicion atacante, Posicion objetivo, Tablero tablero) {
        return objetivo;
    }
}
