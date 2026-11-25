package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.ataque.Ataque;
import edu.fiuba.algo3.modelo.ataque.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.EfectoAtaque;
import edu.fiuba.algo3.modelo.movimiento.MovimientoVek;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.Unidad;

import java.util.List;


public abstract class Vek extends Unidad {
    private final MovimientoVek movimiento;
    private final Ataque ataque;
    private final int danio;
    private final EfectoAtaque efecto;

    protected Vek(int vida, MovimientoVek movimiento, Ataque ataque,
                  int danio, EfectoAtaque efecto) {
        super(vida, movimiento);
        this.movimiento = movimiento;
        this.ataque = ataque;
        this.danio = danio;
        this.efecto = efecto;
    }
    public List<Posicion> casillasAlcanzables(Tablero tablero) {
        return movimiento.posicionesAlcanzables(this, tablero);
    }

    public void mover(Tablero tablero, Posicion destino) {
        if (!destino.equals(obtenerPosicion())) {
            movimiento.mover(this, destino, tablero);
        }
    }

    public AtaqueAnunciado anunciarAtaque(Direccion direccion) {
        return new AtaqueAnunciado(this, direccion, ataque, danio, efecto);
    }

    public Posicion previsualizarImpactoDesde(Posicion origenCandidato,
                                              Direccion direccion, Tablero tablero) {
        return ataque.estimarCasillaAfectada(
                origenCandidato, obtenerPosicion(), direccion, tablero);
    }

    @Override
    public void registrarEn(Tablero tablero) {
        tablero.registrarVek(this);
    }
}


