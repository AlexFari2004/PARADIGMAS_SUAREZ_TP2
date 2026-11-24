package edu.fiuba.algo3.modelo.estado;

import edu.fiuba.algo3.modelo.Unidad;

public class EstadoContaminado implements EstadoUnidad {
    @Override
    public EstadoUnidad recibirDanio(Unidad unidad, int danio) {
        unidad.restarVida(danio * 2);
        return unidad.sinVida() ? new EstadoDestruido() : this;
    }

    @Override
    public EstadoUnidad contaminar() {
        return this; // Regla 3.4: no se acumula
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
