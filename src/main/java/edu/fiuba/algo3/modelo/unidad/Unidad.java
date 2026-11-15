package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;

public abstract class Unidad {
    protected int vida;
    protected Posicion posicion;

    public int obtenerVida() {
        return vida;
    }

    public void recibirDano(int dano) {
        this.vida = vida - dano;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Posicion obtenerPosicion() {
        return posicion;
    }
}