package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueParabolico extends AtaqueBase {
    private static final int RANGO_MINIMO = 2;

    @Override
    protected void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero) {
        boolean mismaFila = atacante.getFila() == objetivo.getFila();
        boolean mismaColumna = atacante.getColumna() == objetivo.getColumna();
        int distancia = atacante.distanciaA(objetivo);

        if (!tablero.estaDentro(objetivo)
                || (!mismaFila && !mismaColumna)
                || distancia < RANGO_MINIMO) {
            throw new IllegalArgumentException(
                    "La artillería requiere un objetivo en línea recta a 2 o más casillas.");
        }
    }

    @Override
    protected Posicion resolverPuntoCaida(Posicion atacante, Posicion objetivo,
                                          Direccion direccion, Tablero tablero) {
        return objetivo;
    }

    @Override
    public Posicion obtenerCasillaAfectada(Posicion atacante, Direccion direccion, Tablero tablero) {
        throw new UnsupportedOperationException(
                "El ataque parabólico requiere una casilla objetivo específica.");
    }
}

