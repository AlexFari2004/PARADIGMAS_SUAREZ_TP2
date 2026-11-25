package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.errores.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.vek.Vek;

public class Emergencia {
    private final Vek vekPorEmerger;
    private final Posicion posicion;

    public Emergencia(Vek vek, Posicion posicion) {
        this.vekPorEmerger = vek;
        this.posicion = posicion;
    }
    public Posicion obtenerPosicion() {
        return posicion;
    }

    public void emerger(Tablero tablero) {
        if (!tablero.estaDentro(posicion)) {
            return;
        }

        Casilla casillaEmergencia = tablero.obtenerCasilla(posicion);

        if (casillaEmergencia.estaOcupada()) {
            casillaEmergencia.recibirDanio(1, tablero, posicion);
            return;
        }

        if (casillaEmergencia.puedeSerOcupada()) {
            tablero.colocar(vekPorEmerger, posicion);
        }
    }
}
