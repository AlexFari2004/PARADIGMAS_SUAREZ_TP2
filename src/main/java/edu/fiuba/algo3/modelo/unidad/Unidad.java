package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.estado.Destruida;
import edu.fiuba.algo3.modelo.unidad.estado.EstadoUnidad;
import edu.fiuba.algo3.modelo.unidad.estado.Operativa;
import edu.fiuba.algo3.modelo.unidad.movilidad.Movilidad;
import edu.fiuba.algo3.modelo.unidad.movilidad.Terrestre;
import edu.fiuba.algo3.modelo.unidad.movimiento.Movible;


public abstract class Unidad {

    private int vida;
    private EstadoUnidad estado;
    protected Posicion posicion;
    protected final Movible movimiento;
    protected final Movilidad movilidad;

    protected Unidad(int vida, Movible movimiento) {
        this(vida, movimiento, new Terrestre());
    }

    protected Unidad(int vida, Movible movimiento, Movilidad movilidad) {
        this.vida = vida;
        this.estado = new Operativa();
        this.movimiento = movimiento;
        this.movilidad = movilidad;
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


    public void recibirDaño(int cantidadDaño) {
        int dañoRecibido = estado.calcularDaño(cantidadDaño);
        vida = Math.max(0, vida - dañoRecibido);
        estado = estado.actualizar(vida);
    }

    public void contaminar() {
        estado = estado.contaminar();
    }

    public void destruir() {
        estado = estado.destruir();
    }

    public void caerAlAgua() {
        movilidad.caerAlAgua(this);
    }

    public void pisarAcido() {
        movilidad.pisarAcido(this);
    }

    public boolean estaMuerta() {
        return estado instanceof Destruida;
    }

    public void hundirse() {
        destruir();
    }

    public void mover(Posicion posicionDestino, Tablero tablero) {
        movimiento.mover(this, posicionDestino, tablero);
    }

    public boolean estaVivo() {
        return !estaMuerta();
    }

    public abstract void registrarEn(Tablero tablero);

    protected EstadoUnidad obtenerEstado() {
        return estado;
    }

    protected void restaurarEstado(EstadoUnidad estadoAnterior) {
        this.estado = estadoAnterior;
    }
}


