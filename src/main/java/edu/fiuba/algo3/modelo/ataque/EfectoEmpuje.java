package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoEmpuje implements EfectoAtaque{

    @Override
    public void aplicarEfecto(Direccion direccion, Posicion impacto, Tablero tablero) {
        Casilla casilla = tablero.obtenerCasilla(impacto);
        if (casilla.estaOcupada()) {
            tablero.empujar(casilla.getUnidad(),direccion);
        }
    }

}
