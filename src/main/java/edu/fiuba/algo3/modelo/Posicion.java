package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;

public class Posicion {

    private static final int LIMITE_INICIAL = 0;
    private final int fila;
    private final int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public List<Posicion> obtenerPosicionesOrtogonales(int filas, int columnas) {
        Posicion[] direccionesOrtogonales = {
                new Posicion(-1,0),
                new Posicion(1, 0),
                new Posicion(0, -1),
                new Posicion(0, 1),
        };

        List<Posicion> posiciones = new ArrayList<>();
        for (Posicion posicion : direccionesOrtogonales) {
            Posicion posicionOrtogonalAdyacente = calcularPosicionOrtogonal(posicion);

            if (posicionOrtogonalAdyacente.estaEnElTablero(filas, columnas)) {
                posiciones.add(posicionOrtogonalAdyacente);
            }
        }
        return posiciones;
    }

    private Posicion calcularPosicionOrtogonal(Posicion posicion) {
        int nuevaFila = this.fila + posicion.fila;
        int nuevaColumna = this.columna + posicion.columna;

        return new Posicion(nuevaFila, nuevaColumna);
    }

    public boolean estaEnElTablero(int filas, int columnas) {
        return this.fila >= LIMITE_INICIAL && this.fila < filas &&
                this.columna >= LIMITE_INICIAL && this.columna < columnas;
    }


    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        return "Posicion{" + "fila=" + fila + ", columna=" + columna + '}';
    }
}
