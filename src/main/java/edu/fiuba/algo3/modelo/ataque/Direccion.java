package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;

import java.util.List;

public class Direccion {
    private final int dFila;
    private final int dColumna;

    private Direccion(int dFila, int dColumna) {
        this.dFila = dFila;
        this.dColumna = dColumna;
    }

    public static final Direccion NORTE = new Direccion(-1, 0);
    public static final Direccion SUR   = new Direccion(1, 0);
    public static final Direccion ESTE  = new Direccion(0, 1);
    public static final Direccion OESTE = new Direccion(0, -1);

    public Posicion desde(Posicion p) {
        return new Posicion(
            p.getFila() + dFila,
            p.getColumna() + dColumna
        );
    }

    public static List<Direccion> todas() {
        return List.of(NORTE, SUR, ESTE, OESTE);
    }

    public static Direccion entre(Posicion origen, Posicion destino) {
        if (origen.equals(destino)) {
            throw new IllegalArgumentException("El origen y el destino son los mismos.");
        }

        int desFila = destino.getFila() - origen.getFila();
        int desColumna = destino.getColumna() - origen.getColumna();

        if (Math.abs(desColumna) >= Math.abs(desFila)) {
            return desColumna >= 0 ? ESTE : OESTE;
        }
        return desFila >= 0 ? SUR : NORTE;
    }
}
