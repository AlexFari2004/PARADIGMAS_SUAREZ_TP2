package edu.fiuba.algo3.modelo.unidad.arma;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

public class Canion {

    public void atacar(Posicion origen, Posicion destino, Tablero tablero) {
        int deltaFila = Integer.compare(destino.getFila(), origen.getFila());
        int deltaColumna = Integer.compare(destino.getColumna(), origen.getColumna());

        int filaActual = origen.getFila() + deltaFila;
        int columnaActual = origen.getColumna() + deltaColumna;

        while (true) {
            Posicion posActual = new Posicion(filaActual, columnaActual);
            Casilla casilla = tablero.obtenerCasilla(posActual);

            if (casilla.estaOcupada()) {
                casilla.getUnidad().recibirDaño(1);
                break;
            }

            if (filaActual == destino.getFila() && columnaActual == destino.getColumna()) {
                break;
            }

            filaActual += deltaFila;
            columnaActual += deltaColumna;
        }
    }
}