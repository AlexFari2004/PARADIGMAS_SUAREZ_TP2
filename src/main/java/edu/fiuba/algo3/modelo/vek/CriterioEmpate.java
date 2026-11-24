package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;

public interface CriterioEmpate {
    int comparar(Posicion a, Posicion b);
}
