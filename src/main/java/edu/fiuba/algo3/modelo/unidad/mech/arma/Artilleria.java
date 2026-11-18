package edu.fiuba.algo3.modelo.unidad.mech.arma;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.casilla.Casilla;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Artilleria {

    public void atacar(Posicion origen, Posicion destino, Tablero tablero) {
        int distancia = origen.distanciaA(destino);
        if (distancia < 2) {
            throw new IllegalArgumentException("La artillería no puede atacar casillas adyacentes.");
        }

        Casilla casillaObjetivo = tablero.obtenerCasilla(destino);
        if (casillaObjetivo.estaOcupada()) {
            casillaObjetivo.getUnidad().recibirDaño(1);
        }

        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            Posicion posicionAdyacente = new Posicion(
                    destino.getFila() + delta[0],
                    destino.getColumna() + delta[1]
            );
            if (!posicionAdyacente.estaEnElTablero(8, 8)) {
                continue;
            }

            Casilla casillaAdyacente = tablero.obtenerCasilla(posicionAdyacente);
            if (casillaAdyacente.estaOcupada()) {
                Posicion posAfuera = new Posicion(
                        destino.getFila() + delta[0] * 2,
                        destino.getColumna() + delta[1] * 2
                );
                if (!posAfuera.estaEnElTablero(8, 8)) {
                    continue;
                }

                Casilla casillaDestinoEmpuje = tablero.obtenerCasilla(posAfuera);
                if (casillaDestinoEmpuje.estaOcupada()) {
                    continue;
                }

                Unidad unidad = casillaAdyacente.getUnidad();
                casillaAdyacente.liberar();
                casillaDestinoEmpuje.ocupar(unidad);
                unidad.actualizarPosicion(posAfuera);
            }
        }
    }
}