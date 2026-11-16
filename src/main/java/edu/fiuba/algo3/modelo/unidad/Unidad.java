package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.estado.Destruida;
import edu.fiuba.algo3.modelo.unidad.estado.EstadoUnidad;
import edu.fiuba.algo3.modelo.unidad.estado.Operativa;

public abstract class Unidad {

    private int vida;
    private EstadoUnidad estado;
    protected Posicion posicion;
    protected final Movible movimiento;

    protected Unidad(int vida, Movible movimiento){
        this.vida = vida;
        this.estado = new Operativa ();
        this.movimiento = movimiento;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void actualizarPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }


    public void recibirDaño(int cantidadDaño){
        int dañoRecibido = estado.calcularDaño(cantidadDaño);
        vida = Math.max(0,vida - dañoRecibido);
        estado = estado.actualizar(vida);
    }

    public void contaminar(){
        estado = estado.contaminar();
    }

    public void destruir() {
        estado = estado.destruir();
    }

    public boolean estaMuerta(){
        return estado instanceof Destruida;
    }

    public void mover(Posicion posicionDestino, Tablero tablero){
        movimiento.mover(this, posicionDestino, tablero);
    }
}