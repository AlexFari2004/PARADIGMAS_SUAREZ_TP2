package edu.fiuba.algo3.modelo.estado;

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
        return this;
    }

    @Override
    public EstadoUnidad actualizar(int vidaRestante) {
        return this;
    }
}
