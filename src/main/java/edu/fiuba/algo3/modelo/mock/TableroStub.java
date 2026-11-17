package edu.fiuba.algo3.modelo.mock;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Suelo;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.unidad.Unidad;


public class TableroStub extends Tablero {

    private final Suelo[][] casillas = new Suelo[0][];

    public TableroStub() {
        super();
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                this.casillas[fila][columna] = new Suelo();
            }
        }
    }

    public void colocarCasilla(Posicion posicion, Casilla casilla) {
        casillas[posicion.obtenerFila()][posicion.obtenerColumna()] = casilla;
    }

    public Casilla obtenerCasilla(Posicion posicion) {
        return casillas[posicion.obtenerFila()][posicion.obtenerColumna()];
    }
}