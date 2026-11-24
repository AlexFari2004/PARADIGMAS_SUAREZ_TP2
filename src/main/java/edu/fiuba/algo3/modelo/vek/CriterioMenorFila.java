package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;

public class CriterioMenorFila implements  CriterioEmpate{
    @Override
    public int comparar(Posicion a, Posicion b) {
        if (a.getFila() != b.getFila()) {
            return Integer.compare(a.getFila(), b.getFila());
        }
        return Integer.compare(a.getColumna(), b.getColumna());
    }
}
