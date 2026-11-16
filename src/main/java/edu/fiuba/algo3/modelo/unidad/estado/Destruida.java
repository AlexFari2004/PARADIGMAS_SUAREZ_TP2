package edu.fiuba.algo3.modelo.unidad.estado;

public class Destruida implements EstadoUnidad {
    @Override
    public int calcularDaño(int daño) {
        return 0;
    }

    @Override
    public EstadoUnidad contaminar() {
        return this;
    }

    @Override
    public EstadoUnidad destruir() {
        return new Destruida();
    }

    @Override
    public EstadoUnidad actualizar(int vidaRestante) {
        return vidaRestante <= 0 ? destruir() : this;
    }
}
