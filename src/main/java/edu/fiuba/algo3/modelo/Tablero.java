package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.mech.Mech;

import java.util.Calendar;

import static edu.fiuba.algo3.modelo.TipoCasilla.*;

public class Tablero {
    private final Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[8][8];
        TipoCasilla[][] tableroPredefinido = {
                {SUELO, AGUA, SUELO, MONTAÑA, SUELO, SUELO, SUELO, SUELO},
                {SUELO, SUELO, SUELO, SUELO, SUELO, EDIFICIO, SUELO, SUELO},
                {SUELO, SUELO, ACIDO, SUELO, AGUA, AGUA, SUELO, SUELO},
                {AGUA, SUELO, SUELO, SUELO, SUELO, SUELO, ACIDO, SUELO},
                {SUELO, SUELO, MONTAÑA, SUELO, SUELO, SUELO, SUELO, MONTAÑA},
                {SUELO, AGUA, SUELO, EDIFICIO, SUELO, SUELO, SUELO, SUELO},
                {SUELO, SUELO, AGUA, SUELO, SUELO, AGUA, SUELO, SUELO},
                {ACIDO, SUELO, SUELO, ACIDO, SUELO, SUELO, SUELO, EDIFICIO}
        };

        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                casillas[f][c] = new Casilla(f, c, tableroPredefinido[f][c]);
            }
        }
    }

    public Casilla obtenerCasilla(Posicion posicion) {
        return casillas[posicion.obtenerFila()][posicion.obtenerColumna()];
    }

    public void colocarUnidad(Mech mech, Posicion posicion) {
        Casilla casilla = obtenerCasilla(posicion);
        casilla.colocarUnidad(mech);
        mech.setPosicion(posicion);
    }
}