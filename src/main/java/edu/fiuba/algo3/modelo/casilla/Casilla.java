package edu.fiuba.algo3.modelo.casilla;

import edu.fiuba.algo3.modelo.errors.unidad.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.efectos.EfectoAtaque;

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

    public void liberar() {
        this.unidad = null;
    }

    public Unidad getUnidad() {
        return this.unidad;
    }

    public abstract TipoCasilla getTipo();

    public void recibirDaño(int daño) {
        this.unidad.recibirDaño(daño);
    }

    public void recibirAtaque(int daño, EfectoAtaque efecto) {
        if (!estaOcupada()) return;

        Unidad ocupante = this.unidad;
        recibirDaño(daño);
        if (ocupante != null) {
            efecto.aplicar(ocupante);
        }
    }
}