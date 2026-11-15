package edu.fiuba.algo3.modelo.unidad.mech.arma;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.mock.TableroStub;

public class Canion {

    public void atacar(Posicion origen, Posicion destino, TableroStub tablero) {
        int deltaFila = Integer.compare(destino.obtenerFila(), origen.obtenerFila());
        int deltaColumna = Integer.compare(destino.obtenerColumna(), origen.obtenerColumna());

        int filaActual = origen.obtenerFila() + deltaFila;
        int columnaActual = origen.obtenerColumna() + deltaColumna;

        while (true) {
            Posicion posActual = new Posicion(filaActual, columnaActual);
            Casilla casilla = tablero.obtenerCasilla(posActual);

            if (casilla.estaOcupado()) {
                casilla.obtenerUnidad().recibirDano(1);
                break;
            }

            if (filaActual == destino.obtenerFila() && columnaActual == destino.obtenerColumna()) {
                break;
            }

            filaActual += deltaFila;
            columnaActual += deltaColumna;
        }
    }
}