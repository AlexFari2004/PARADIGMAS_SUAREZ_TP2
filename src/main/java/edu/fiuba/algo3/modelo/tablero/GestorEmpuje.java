package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.ataque.Direccion;

public class GestorEmpuje {
    private final Tablero tablero;
    private static final int DANIO_COLISION = 1;

    public GestorEmpuje(Tablero tablero) {
        this.tablero = tablero;
    }

    public void empujar(Unidad unidad, Direccion direccion) {
        if (unidad == null || !unidad.estaVivo()) {
            return;
        }

        Posicion origen = unidad.obtenerPosicion();
        Posicion destino = direccion.desplazarHacia(origen);

        if (!tablero.estaDentro(destino)) {
            return;
        }

        Casilla casillaOrigen = tablero.obtenerCasilla(origen);
        Casilla casillaDestino = tablero.obtenerCasilla(destino);


        if (casillaDestino.puedeSerOcupada()) {
            casillaOrigen.liberar();
            unidad.actualizarPosicion(destino);
            casillaDestino.ocupar(unidad);
            if (!unidad.estaVivo()) {
                casillaDestino.liberar();
            }
            return;
        }

        unidad.recibirDanio(DANIO_COLISION);
        if (!unidad.estaVivo()) {
            casillaOrigen.liberar();
        }

        casillaDestino.recibirDanio(DANIO_COLISION, tablero, destino);
    }
}
