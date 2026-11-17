package edu.fiuba.algo3.modelo.unidad.estado;

public class Operativa implements EstadoUnidad {

    @Override
    public int calcularDaño(int daño) {
        return daño;
    }

    @Override
    public EstadoUnidad contaminar() {
        return new Contaminada();
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
