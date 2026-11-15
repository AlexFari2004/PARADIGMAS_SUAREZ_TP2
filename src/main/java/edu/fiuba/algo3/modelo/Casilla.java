package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Casilla {
    private final int fila;
    private final int columna;
    private final TipoCasilla tipo;
    private Unidad unidadOcupante;

    public Casilla(int fila, int columna, TipoCasilla tipo) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }

    public boolean estaOcupado() {
        return unidadOcupante != null;
    }

    public boolean esObstaculo() {
        return tipo == TipoCasilla.MONTAÑA || tipo == TipoCasilla.EDIFICIO;
    }

    public void colocarUnidad(Unidad unidad) {
        this.unidadOcupante = unidad;
    }

    public void liberar() {
        this.unidadOcupante = null;
    }

    public Unidad obtenerUnidad() {
        return unidadOcupante;
    }

}