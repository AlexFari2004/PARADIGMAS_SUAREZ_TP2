package edu.fiuba.algo3.modelo.estado;

import edu.fiuba.algo3.modelo.Unidad;

public class EstadoNormal implements EstadoUnidad{
    @Override
    public EstadoUnidad recibirDanio(Unidad unidad, int danio) {
        unidad.restarVida(danio);
        return unidad.sinVida() ? new EstadoDestruido() : this;
    }

    @Override
    public EstadoUnidad contaminar() {
        return new EstadoContaminado();
    }

    @Override
    public EstadoUnidad destruir() {
        return new EstadoDestruido();
    }

    @Override
    public boolean estaVivo() {
        return true;
    }
}
