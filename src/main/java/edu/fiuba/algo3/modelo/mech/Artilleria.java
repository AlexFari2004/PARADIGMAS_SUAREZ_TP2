package edu.fiuba.algo3.modelo.mech;

import edu.fiuba.algo3.modelo.ataque.Ataque;
import edu.fiuba.algo3.modelo.ataque.AtaqueParabolico;
import edu.fiuba.algo3.modelo.ataque.EfectoAtaque;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class Artilleria implements Arma {

    private final int danio;
    private final EfectoAtaque efecto;
    private final Ataque ataque = new AtaqueParabolico();

    public Artilleria(int danio, EfectoAtaque efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override
    public void atacar(Posicion atacante, Posicion objetivo, Tablero tablero) {
        ataque.generarAtaque(atacante, objetivo, tablero, danio, efecto);
    }
}