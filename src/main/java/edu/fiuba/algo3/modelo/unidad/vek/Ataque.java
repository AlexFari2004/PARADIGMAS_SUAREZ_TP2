package edu.fiuba.algo3.modelo.unidad.vek;


import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.casilla.Casilla;
import edu.fiuba.algo3.modelo.unidad.efecto.EfectoAtaque;

public class Ataque {
    private final Posicion destino;
    private final Posicion origen;
    private final int dañoDeAtaque;
    private final EfectoAtaque efecto;

    public Ataque(Posicion origen, Posicion destino, int dañoDeAtaque, EfectoAtaque efecto) {
        this.origen = origen;
        this.destino = destino;
        this.dañoDeAtaque = dañoDeAtaque;
        this.efecto = efecto;
    }

    public void aplicar(Tablero tablero) {
        Casilla casilla = tablero.obtenerCasilla(destino);
        if (casilla.estaOcupada()) {

            casilla.recibirAtaque(dañoDeAtaque, efecto);
        }
    }
}
