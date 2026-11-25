package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Posicion;

import java.util.List;

public class Direccion {
    private final int desplFila;
    private final int despldColumna;

    private Direccion(int desplFila, int despldColumna) {
        this.desplFila = desplFila;
        this.despldColumna = despldColumna;
    }

    public static final Direccion ARRIBA = new Direccion(-1, 0);
    public static final Direccion ABAJO   = new Direccion(1, 0);
    public static final Direccion DERECHA  = new Direccion(0, 1);
    public static final Direccion IZQUIERDA = new Direccion(0, -1);

    public Posicion desplazarHacia(Posicion p) {
        return new Posicion(
            p.getFila() + desplFila,
            p.getColumna() + despldColumna
        );
    }

    public static List<Direccion> obtenerDirecciones() {
        return List.of(ARRIBA, ABAJO, DERECHA, IZQUIERDA);
    }

    public static Direccion reconocerDireccion(Posicion origen, Posicion destino) {
        if (origen.equals(destino)) {
            throw new IllegalArgumentException("El origen y el destino son los mismos.");
        }

        int distanciaFila = destino.getFila() - origen.getFila();
        int distanciaColumna = destino.getColumna() - origen.getColumna();

        if (Math.abs(distanciaColumna) >= Math.abs(distanciaFila)) {
            return distanciaColumna >= 0 ? DERECHA : IZQUIERDA;
        }
        return distanciaFila >= 0 ? ABAJO : ARRIBA;
    }
}
