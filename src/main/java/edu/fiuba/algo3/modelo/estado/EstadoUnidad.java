package edu.fiuba.algo3.modelo.mech;

public interface EstadoUnidad {

    int calcularDaño(int daño);

    EstadoUnidad contaminar();

    EstadoUnidad destruir();

    EstadoUnidad actualizar(int vidaRestante);
}
