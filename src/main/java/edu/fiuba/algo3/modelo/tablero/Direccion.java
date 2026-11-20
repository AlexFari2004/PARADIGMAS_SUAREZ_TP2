package edu.fiuba.algo3.modelo.tablero;

public record Direccion(int deltaFila, int deltaColumna) {

    public Direccion {
        if ((deltaFila != 0) == (deltaColumna != 0)) {
            throw new IllegalArgumentException("Las posiciones no están en línea recta.");
        }
    }

    static Direccion entre(Posicion origen, Posicion destino) {
        int deltaFila = Integer.signum(destino.getFila() - origen.getFila());
        int deltaColumna = Integer.signum(destino.getColumna() - origen.getColumna());
        return new Direccion(deltaFila,deltaColumna);
    }

    Posicion aplicarA(Posicion posicion) {
        int nuevaFila = posicion.getFila() + deltaFila;
        int nuevaColumna = posicion.getColumna() + deltaColumna;
        return new Posicion(nuevaFila, nuevaColumna);
    }
}