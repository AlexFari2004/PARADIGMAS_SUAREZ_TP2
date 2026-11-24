package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class AtaqueBase implements Ataque {
    @Override
    public final void generarAtaque(Posicion atacante, Posicion objetivo, Tablero tablero,
                                    int danio, EfectoAtaque efecto) {
        validarAlcance(atacante, objetivo, tablero);
        Posicion impacto = calcularImpacto(atacante, objetivo, tablero);
        tablero.obtenerCasilla(impacto).recibirDanio(danio);
        efecto.aplicarEfecto(atacante, impacto, tablero);
    }

    protected abstract void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero);

    protected abstract Posicion calcularImpacto(Posicion atacante, Posicion objetivo, Tablero tablero);
}
