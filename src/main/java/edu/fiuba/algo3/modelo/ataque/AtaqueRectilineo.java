package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueRectilineo extends AtaqueBase {
    @Override
    protected void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero) {
        boolean mismaFila = atacante.getFila() == objetivo.getFila();
        boolean mismaColumna = atacante.getColumna() == objetivo.getColumna();
        if (!tablero.estaDentro(objetivo) || atacante.equals(objetivo) || (!mismaFila && !mismaColumna)) {
            throw new IllegalArgumentException("El objetivo debe estar en línea recta.");
        }
    }

    @Override
    public Posicion obtenerCasillaAfectada(Posicion atacante, Direccion direccion, Tablero tablero) {
        return estimarCasillaAfectada(atacante, atacante, direccion, tablero);
    }

    @Override
    public Posicion estimarCasillaAfectada(Posicion origen, Posicion posicionAIgnorar,
                                           Direccion direccion, Tablero tablero) {
        Posicion actual = direccion.desplazarHacia(origen);
        while (tablero.estaDentro(actual)) {
            if (!actual.equals(posicionAIgnorar) && tablero.obtenerCasilla(actual).detieneAtaque()) {
                return actual;
            }
            actual = direccion.desplazarHacia(actual);
        }
        return actual;
    }
}
