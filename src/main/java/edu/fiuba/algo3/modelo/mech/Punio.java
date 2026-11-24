package edu.fiuba.algo3.modelo.mech;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.AtaqueAdyacente;
import edu.fiuba.algo3.modelo.vek.EfectoAtaque;

public class Punio implements Arma {

    private final int danio;
    private final EfectoAtaque efecto;

    public Punio(int danio, EfectoAtaque efecto) { this.danio = danio; this.efecto = efecto; }

    @Override
    public void atacar(Posicion atacante, Posicion objetivo, Tablero tablero) {
        new AtaqueAdyacente().generarAtaque(atacante, objetivo, tablero, danio, efecto);
    }
}
