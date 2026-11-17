package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

public abstract class Unidad {

    private int vida;
    private Dañable daño;
    protected Posicion posicion;
    protected final Movible movimiento;

    protected Unidad(int vida, Movible movimiento){
        this.vida = vida;
        this.daño = new DañoNoContaminada();
        this.movimiento = movimiento;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void actualizarPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }


    public void recibirDaño(int cantidadDaño){
        int dañoRecibido = daño.calcularDaño(cantidadDaño);
        vida = vida - dañoRecibido;

        if (vida < 0){
            vida = 0;
        }
    }

    public void contaminar(){
        daño = new DañoContaminada();
    }

    public boolean estaMuerta(){
        return vida == 0;
    }

    public void mover(Posicion posicionDestino, Tablero tablero){
        movimiento.mover(this, posicionDestino, tablero);
    }

    public abstract void registrarEn(Tablero tablero);
}