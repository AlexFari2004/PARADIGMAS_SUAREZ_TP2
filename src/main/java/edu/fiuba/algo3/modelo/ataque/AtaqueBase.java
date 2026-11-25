package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class AtaqueBase implements Ataque {
    @Override
    public final void generarAtaque(Posicion atacante, Posicion objetivo, Tablero tablero,
                                    int danio, EfectoAtaque efecto) {
        validarAlcance(atacante, objetivo, tablero);
        Direccion direccion = Direccion.reconocerDireccion(atacante, objetivo);
        Posicion impacto = resolverPuntoCaida(atacante, objetivo, direccion, tablero);

        if (tablero.estaDentro(impacto)) {
            aplicarEn(direccion, impacto, tablero, danio, efecto);
        }
    }

    @Override
    public final void generarAtaqueEnDireccion(Posicion atacante, Direccion direccion, Tablero tablero,
                                               int danio, EfectoAtaque efecto) {
        Posicion impacto = obtenerCasillaAfectada(atacante, direccion, tablero);

        if (tablero.estaDentro(impacto)) {
            aplicarEn(direccion, impacto, tablero, danio, efecto);
        }
    }

    protected Posicion resolverPuntoCaida(Posicion atacante, Posicion objetivo,
                                          Direccion direccion, Tablero tablero) {
        return obtenerCasillaAfectada(atacante, direccion, tablero);
    }

    private void aplicarEn(Direccion direccion, Posicion impacto, Tablero tablero,
                           int danio, EfectoAtaque efecto) {
        tablero.obtenerCasilla(impacto).recibirDanio(danio, tablero, impacto);
        efecto.aplicarEfecto(direccion, impacto, tablero);
    }

    protected abstract void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero);

    @Override
    public Posicion estimarCasillaAfectada(Posicion origen, Posicion posicionAIgnorar,
                                           Direccion direccion, Tablero tablero) {
        return obtenerCasillaAfectada(origen, direccion, tablero);
    }
}
