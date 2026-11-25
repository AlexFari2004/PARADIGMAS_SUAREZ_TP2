package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class AtaqueBase implements Ataque {
    @Override
    public final void generarAtaque(Posicion atacante, Posicion objetivo, Tablero tablero,
                                    int danio, EfectoAtaque efecto) {
        validarAlcance(atacante, objetivo, tablero);
        Direccion direccion = Direccion.entre(atacante, objetivo);
        Posicion impacto = resolverPuntoImpacto(atacante, objetivo, direccion, tablero);

        if (tablero.estaDentro(impacto)) {
            impactarEn(direccion, impacto, tablero, danio, efecto);
        }
    }

    @Override
    public final void generarAtaqueEnDireccion(Posicion atacante, Direccion direccion, Tablero tablero,
                                               int danio, EfectoAtaque efecto) {
        Posicion impacto = calcularImpacto(atacante, direccion, tablero);

        if (tablero.estaDentro(impacto)) {
            impactarEn(direccion, impacto, tablero, danio, efecto);
        }
    }

    protected Posicion resolverPuntoImpacto(Posicion atacante, Posicion objetivo,
                                            Direccion direccion, Tablero tablero) {
        return calcularImpacto(atacante, direccion, tablero);
    }

    private void impactarEn(Direccion direccion, Posicion impacto, Tablero tablero,
                            int danio, EfectoAtaque efecto) {
        tablero.obtenerCasilla(impacto).recibirDanio(danio, tablero, impacto);
        efecto.aplicarEfecto(direccion, impacto, tablero);
    }

    protected abstract void validarAlcance(Posicion atacante, Posicion objetivo, Tablero tablero);

    @Override
    public Posicion calcularImpactoDesdeSimulacion(Posicion origen, Posicion posicionAIgnorar,
                                                   Direccion direccion, Tablero tablero) {
        return calcularImpacto(origen, direccion, tablero);
    }
}
