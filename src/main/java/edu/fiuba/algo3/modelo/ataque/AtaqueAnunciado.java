package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.Vek;

public class AtaqueAnunciado {
    private final Vek atacante;
    private final Direccion direccion;
    private final Ataque ataque;
    private final int danio;
    private final EfectoAtaque efecto;

    public AtaqueAnunciado(Vek atacante, Direccion direccion, Ataque ataque,
                           int danio, EfectoAtaque efecto) {
        this.atacante = atacante;
        this.direccion = direccion;
        this.ataque = ataque;
        this.danio = danio;
        this.efecto = efecto;
    }

    public void resolver(Tablero tablero) {
        if (!atacante.estaVivo()) {
            return;
        }
        ataque.generarAtaqueEnDireccion(atacante.obtenerPosicion(), direccion, tablero, danio, efecto);
    }

    public Posicion obtenerPosicionImpactoActual(Tablero tablero) {
        return ataque.calcularImpacto(atacante.obtenerPosicion(), direccion, tablero);
    }
}
