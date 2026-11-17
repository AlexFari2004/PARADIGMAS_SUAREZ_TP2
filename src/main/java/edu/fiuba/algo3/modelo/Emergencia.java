package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.errors.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.unidad.Vek;

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
