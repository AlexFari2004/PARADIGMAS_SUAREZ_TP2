package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.errores.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.vek.EfectoAtaque;

public abstract class Casilla {
    private Ocupante ocupante = new SinOcupante();
    private Unidad unidad;

    public void ocupar(Unidad unidad) {
        if (estaOcupada()) {
            throw new MovimientoEnPosicionOcupadaException();
        }

        this.unidad = unidad;
        aplicarEfecto(unidad);
    }

    protected void aplicarEfecto(Unidad unidad) {
    }


    public void validarAtaqueDesdeAca() {
    }

    public boolean estaOcupada() {
        return this.unidad != null;
    }

    public void liberar() {
        this.unidad = null;
    }

    public Unidad getUnidad() {
        return this.unidad;
    }

    public abstract TipoCasilla getTipo();

    public void recibirDanio(int danio) {
        this.unidad.recibirDanio(danio);
    }

    public void recibirAtaque(int danio, EfectoAtaque efecto) {
        if (!estaOcupada()) return;

        Unidad ocupante = this.unidad;
        recibirDanio(danio);
        if (ocupante != null) {
            efecto.aplicar(ocupante);
        }
    }
}