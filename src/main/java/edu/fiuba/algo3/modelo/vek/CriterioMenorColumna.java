package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;

public class CriterioMenorColumna implements CriterioEmpate{
    @Override
    public int comparar(Posicion a, Posicion b) {
        if (a.getColumna() != b.getColumna()) {
            return Integer.compare(a.getColumna(), b.getColumna());
        }
        return Integer.compare(a.getFila(), b.getFila());
    }
}
