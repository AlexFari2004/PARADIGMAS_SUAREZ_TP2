package edu.fiuba.algo3.modelo.estado;

public class Contaminada implements EstadoUnidad {
    private static final int MULTIPLICADOR_CONTAMINACION = 2;

    @Override
    public int calcularDaño(int daño) {
        return daño * MULTIPLICADOR_CONTAMINACION;
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
