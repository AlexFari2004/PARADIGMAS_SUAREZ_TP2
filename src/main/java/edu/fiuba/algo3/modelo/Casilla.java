package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.errors.MovimientoEnPosicionOcupadaException;

public abstract class Casilla {

    private Unidad unidad;

    public void ocupar(Unidad unidad) {
        if (estaOcupada()) {
            throw new MovimientoEnPosicionOcupadaException();
        }

        this.unidad = unidad;
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
}