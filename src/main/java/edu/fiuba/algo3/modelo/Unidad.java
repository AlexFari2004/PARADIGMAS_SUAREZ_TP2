package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.estado.EstadoNormal;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.estado.EstadoUnidad;
import edu.fiuba.algo3.modelo.movimiento.Movible;


public abstract class Unidad {

    private int vida;
    private EstadoUnidad estado;
    protected Posicion posicion;
    protected final Movible movimiento;

    protected Unidad(int vida, Movible movimiento) {
        this.vida = vida;
        this.estado = new EstadoNormal();
        this.movimiento = movimiento;
    }

    public Posicion obtenerPosicion() {
        return posicion;
    }

    public int obtenerVida() {
        return vida;
    }

    public void actualizarPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }


    public void recibirDanio(int cantidadDanio) {
        this.estado = estado.recibirDanio(this, cantidadDanio);
    }

    public void contaminar() {
        estado = estado.contaminar();
    }

    public void destruir() {
        estado = estado.destruir();
    }

    public void restarVida(int cantidad) {
        this.vida = Math.max(0, this.vida - cantidad);
    }

    public boolean sinVida() {
        return this.vida == 0;
    }

    public boolean estaVivo() {
        return estado.estaVivo();
    }

    public boolean estaMuerta() {
        return !estaVivo();
    }

    public void sumergir() {
        movimiento.sumergir(this);
    }

    public void corroer() {
        movimiento.corroer(this);
    }

    public void mover(Posicion posicionDestino, Tablero tablero) {
        movimiento.mover(this, posicionDestino, tablero);
    }

    public abstract void registrarEn(Tablero tablero);

    public EstadoUnidad obtenerEstado() {
        return estado;
    }

    protected void restaurarEstado(EstadoUnidad estadoAnterior) {
        this.estado = estadoAnterior;
    }
}


