package edu.fiuba.algo3.modelo.unidad.arma;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.mock.TableroStub;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Artilleria {

    public void atacar(Posicion origen, Posicion destino, TableroStub tablero) {
        int distancia = origen.calcularDistanciaManhattan(destino);
        if (distancia < 2) {
            throw new IllegalArgumentException("La artillería no puede atacar casillas adyacentes (distancia < 2)[cite: 6].");
        }

        // 1. Dañar el objetivo principal
        Casilla casillaObjetivo = tablero.obtenerCasilla(destino);
        if (casillaObjetivo.estaOcupado()) {
            casillaObjetivo.obtenerUnidad().recibirDano(1);
        }

        // 2. Empujar hacia afuera a las 4 casillas adyacentes del objetivo
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            Casilla casillaAdyacente = tablero.obtenerCasillaAdyacente(destino, delta[0], delta[1]);
            if (casillaAdyacente.estaOcupado()) {
                Unidad unidad = casillaAdyacente.obtenerUnidad();
                casillaAdyacente.liberar();

                // Desplaza la unidad 1 casilla adicional hacia afuera en la misma dirección del delta
                Posicion posAfuera = new Posicion(
                        destino.obtenerFila() + delta[0] * 2,
                        destino.obtenerColumna() + delta[1] * 2
                );
                Casilla casillaDestinoEmpuje = tablero.obtenerCasilla(posAfuera);
                casillaDestinoEmpuje.colocarUnidad(unidad);
            }
        }
    }
}