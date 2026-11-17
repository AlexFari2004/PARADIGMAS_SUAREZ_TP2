package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.estado.Destruida;
import edu.fiuba.algo3.modelo.unidad.estado.EstadoUnidad;
import edu.fiuba.algo3.modelo.unidad.estado.Operativa;
import edu.fiuba.algo3.modelo.unidad.movilidad.Movilidad;
import edu.fiuba.algo3.modelo.unidad.movilidad.Terrestre;


public abstract class Unidad {

    private int vida;
    private EstadoUnidad estado;
    protected Posicion posicion;
    protected final Movible movimiento;
    protected final Movilidad movilidad;

    protected Unidad(int vida, Movible movimiento){
        this.vida = vida;
        this.estado = new Operativa ();
        this.movimiento = movimiento;
        this.movilidad = new Terrestre();
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public int obtenerVida() {
        return vida;
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

    public void caerAlAgua(){
        movilidad.caerAlAgua(this);
    }

    public void pisarAcido(){
        movilidad.pisarAcido(this);
    }

    public boolean estaMuerta(){
        return estado instanceof Destruida;
    }

    public boolean hundirse() {
        return estado instanceof Destruida;
    }
    public void mover(Posicion posicionDestino, Tablero tablero){
        movimiento.mover(this, posicionDestino, tablero);
    }

    public abstract void registrarEn(Tablero tablero);
}