package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casilla.Casilla;

public class Ataque {
    private final Posicion destino;
    private final Posicion origen;
    private final int dañoDeAtaque;

    public Ataque(Posicion origen, Posicion destino, int dañoDeAtaque) {
        this.origen = origen;
        this.destino = destino;
        this.dañoDeAtaque = dañoDeAtaque;
    }

    public void aplicar(Tablero tablero) {
        Casilla casilla = tablero.obtenerCasilla(destino);
        if (casilla.estaOcupada()) {
            casilla.recibirDaño(dañoDeAtaque);
        }
    }
}
