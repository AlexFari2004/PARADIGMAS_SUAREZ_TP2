package edu.fiuba.algo3.modelo.mech;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.ataque.Ataque;
import edu.fiuba.algo3.modelo.ataque.AtaqueRectilineo;
import edu.fiuba.algo3.modelo.ataque.EfectoAtaque;

public class Canion implements Arma {
    private final int danio;
    private final EfectoAtaque efecto;
    private final Ataque ataque = new AtaqueRectilineo();

    public Canion(int danio, EfectoAtaque efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override
    public void atacar(Posicion atacante, Posicion objetivo, Tablero tablero) {
        ataque.generarAtaque(atacante, objetivo, tablero, danio, efecto);
    }

}