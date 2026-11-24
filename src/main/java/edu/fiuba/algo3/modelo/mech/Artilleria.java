package edu.fiuba.algo3.modelo.mech;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.vek.*;

public class Artilleria implements Arma {

    private final int danio;
    private final EfectoAtaque efecto;
    private final Ataque ataque = new AtaqueParabolico(2);

    public Artilleria(int danio, EfectoAtaque efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override
    public void atacar(Posicion atacante, Posicion objetivo, Tablero tablero) {
        ataque.generarAtaque(atacante, objetivo, tablero, danio, efecto);
    }
}