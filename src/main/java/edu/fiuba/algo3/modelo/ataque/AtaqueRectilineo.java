package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.mech.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueRectilineo extends AtaqueBase {
    @Override
    protected void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero) {
        boolean mismaFila = atacante.getFila() == objetivo.getFila();
        boolean mismaColumna = atacante.getColumna() == objetivo.getColumna();
        boolean esElMismo = mismaFila && mismaColumna;

        if (!tablero.estaDentro(objetivo) || esElMismo || (!mismaFila && !mismaColumna)) {
            throw new IllegalArgumentException("Fuera de alcance");
        }
    }

    @Override
    protected Posicion calcularImpacto(Posicion atacante, Posicion objetivo, Tablero tablero) {
        Direccion direccion = Direccion.entre(atacante, objetivo);

        Posicion actual = direccion.desde(atacante);
        Posicion ultimaDentro = actual;

        while (tablero.estaDentro(actual)) {
            if (tablero.obtenerCasilla(actual).bloqueaMovimiento()) {
                return actual;
            }
            ultimaDentro = actual;
            actual = direccion.desde(actual);
        }
        return ultimaDentro;
    }
}
