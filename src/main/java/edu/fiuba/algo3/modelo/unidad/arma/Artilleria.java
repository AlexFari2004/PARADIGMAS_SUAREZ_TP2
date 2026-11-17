package edu.fiuba.algo3.modelo.unidad.arma;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Artilleria {

    public void atacar(Posicion origen, Posicion destino, Tablero tablero) {
        int distancia = origen.distanciaA(destino);
        if (distancia < 2) {
            throw new IllegalArgumentException("La artillería no puede atacar casillas adyacentes (distancia < 2)[cite: 6].");
        }

        // 1. Dañar el objetivo principal
        Casilla casillaObjetivo = tablero.obtenerCasilla(destino);
        if (casillaObjetivo.estaOcupada()) {
            casillaObjetivo.getUnidad().recibirDaño(1);
        }

        // 2. Empujar hacia afuera a las 4 casillas adyacentes del objetivo
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            Casilla casillaAdyacente = tablero.obtenerCasillaAdyacente(destino, delta[0], delta[1]);
            if (casillaAdyacente.estaOcupada()) {
                Unidad unidad = casillaAdyacente.getUnidad();
                casillaAdyacente.liberar();

                // Desplaza la unidad 1 casilla adicional hacia afuera en la misma dirección del delta
                Posicion posAfuera = new Posicion(
                        destino.getFila() + delta[0] * 2,
                        destino.getColumna() + delta[1] * 2
                );
                Casilla casillaDestinoEmpuje = tablero.obtenerCasilla(posAfuera);
                casillaDestinoEmpuje.ocupar(unidad);
            }
        }
    }
}