package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoEmpujeOrtogonal implements EfectoAtaque{
    @Override
    public void aplicarEfecto(Direccion direccion, Posicion impacto, Tablero tablero) {

        for (Direccion dirHaciaAfuera : Direccion.todas()) {
            Posicion adyacente = dirHaciaAfuera.desde(impacto);
            if (tablero.estaDentro(adyacente)) {
                Casilla casillaAdyacente = tablero.obtenerCasilla(adyacente);
                if (casillaAdyacente.estaOcupada()) {
                    tablero.empujar(casillaAdyacente.getUnidad(), dirHaciaAfuera);
                }
            }
        }
    }
}
