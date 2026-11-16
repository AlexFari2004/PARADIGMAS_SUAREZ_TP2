package edu.fiuba.algo3.modelo;

public class Emergencia {
    private final Vek vek;
    private final Posicion posicion;
    public Emergencia(Vek vek, Posicion posicion) {
        this.vek = vek;
        this.posicion = posicion;
    }

    public void emeger() {
        posicion.obtenerCasilla.agregar(vek);
    }
}
