package edu.fiuba.algo3.modelo.estado;

import edu.fiuba.algo3.modelo.Unidad;

public class EstadoDestruido implements EstadoUnidad {
    @Override
    public EstadoUnidad recibirDanio(Unidad unidad, int danio) {
        return this;
    }

    @Override
    public EstadoUnidad contaminar() {
        return this;
    }

    @Override
    public EstadoUnidad destruir() {
        return this;
    }

    @Override
    public boolean estaVivo() {
        return false;
    }
}
