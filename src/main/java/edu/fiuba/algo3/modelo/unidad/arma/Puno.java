package edu.fiuba.algo3.modelo.unidad.arma;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Puno {

    public void atacar(Posicion origen, Posicion destino, Tablero tablero) {
        if (origen.distanciaA(destino) != 1) {
            throw new IllegalArgumentException("El puño solo puede atacar una casilla adyacente.");
        }

        Casilla casillaObjetivo = tablero.obtenerCasilla(destino);
        if (!casillaObjetivo.estaOcupada()) {
            return;
        }

        Unidad unidad = casillaObjetivo.getUnidad();
        unidad.recibirDaño(2);

        int deltaFila = destino.getFila() - origen.getFila();
        int deltaColumna = destino.getColumna() - origen.getColumna();
        Posicion posicionEmpujada = new Posicion(
                destino.getFila() + deltaFila,
                destino.getColumna() + deltaColumna
        );

        if (!posicionEmpujada.estaEnElTablero(8, 8)) {
            return;
        }

        Casilla casillaEmpuje = tablero.obtenerCasilla(posicionEmpujada);
        if (casillaEmpuje.estaOcupada()) {
            return;
        }

        casillaObjetivo.liberar();
        casillaEmpuje.ocupar(unidad);
        unidad.actualizarPosicion(posicionEmpujada);
    }
}
