package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.errores.casillas.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.casilla.Casilla;
import edu.fiuba.algo3.modelo.unidad.vek.Vek;

public class Emergencia {
    private final Vek vekPorEmerger;
    private final Posicion posicion;

    public Emergencia(Vek vek, Posicion posicion) {
        this.vekPorEmerger = vek;
        this.posicion = posicion;
    }

    public void emerger(Tablero tablero) {
        Casilla casillaEmergencia = tablero.obtenerCasilla(posicion);
        if (casillaEmergencia.estaOcupada()) {
            casillaEmergencia.recibirDaño(1);
            throw new CasillaNoTransitableException();
        }
        casillaEmergencia.ocupar(vekPorEmerger);
    }
}
