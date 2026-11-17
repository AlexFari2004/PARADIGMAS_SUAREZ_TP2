package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.unidad.Dañable;
import edu.fiuba.algo3.modelo.unidad.DañoContaminada;

public class Ataque {
    private final Posicion destino;
    private final Posicion origen;
    private final Dañable daño;
    private final int dañoDeAtaque;

    public Ataque(Posicion origen, Posicion destino, Dañable daño, int dañoDeAtaque) {
        this.origen = origen;
        this.destino = destino;
        this.daño = daño;
        this.dañoDeAtaque = dañoDeAtaque;
    }

    public void aplicar(Tablero tablero) {
        Casilla casilla = tablero.obtenerCasilla(destino);
        if (casilla.estaOcupada()) {
            casilla.recibirDaño(daño.calcularDaño(this.dañoDeAtaque));
        }
    }
}
