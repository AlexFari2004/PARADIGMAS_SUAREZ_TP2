package edu.fiuba.algo3.modelo;

public class Ataque {
    private final Posicion destino;
    private final Posicion origen
    private final int daño;

    public Ataque(Posicion origen, Posicion destino, int daño) {
        this.origen = origen;
        this.destino = destino;
        this.daño = daño;
    }

    public void aplicar(Tablero tablero) {
        Casilla casilla = tablero.obtenerTerreno(destino);
        if (casilla.estaOcupado()) {
            //Edificio tiene que recibir el daño
            casilla.obtenerUnidad().recibirDaño(daño);
        }
        casilla.recibirDaño(daño);
    }

}
