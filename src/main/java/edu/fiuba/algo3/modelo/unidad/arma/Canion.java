package edu.fiuba.algo3.modelo.unidad.arma;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casilla.TipoCasilla;

public class Canion {

    public void atacar(Posicion origen, Posicion destino, Tablero tablero) {
        boolean mismaFila = origen.getFila() == destino.getFila();
        boolean mismaColumna = origen.getColumna() == destino.getColumna();
        if (mismaFila == mismaColumna) {
            throw new IllegalArgumentException("El cañón solo puede atacar en línea recta.");
        }

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

            TipoCasilla tipo = casilla.getTipo();
            if (tipo == TipoCasilla.MONTAÑA || tipo == TipoCasilla.EDIFICIO) {
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