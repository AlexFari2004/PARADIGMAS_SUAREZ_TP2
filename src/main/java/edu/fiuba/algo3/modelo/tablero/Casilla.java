package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.errores.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.Unidad;

public abstract class Casilla {
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

    public boolean puedeSerOcupada() {
        return !estaOcupada();
    }

    public void liberar() {
        this.unidad = null;
    }

    public Unidad getUnidad() {
        return this.unidad;
    }

    public abstract TipoCasilla getTipo();

    public void recibirDanio(int danio, Tablero tablero, Posicion posicion) {
        if (!estaOcupada()) {
            return;
        }
        this.unidad.recibirDanio(danio);
        if (!this.unidad.estaVivo()) {
            liberar();
        }
    }
    public boolean detieneAtaque() {
        return estaOcupada();
    }

}