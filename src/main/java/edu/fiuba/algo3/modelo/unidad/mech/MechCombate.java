package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.Mech;

public class MechCombate extends Mech {
    private final Puno puno;

    public MechCombate(Puno puno) {
        this.puno = puno;
        this.vida = 3; // El Mech de combate tiene 3 puntos de vida
    }

    public void atacar(Posicion destino, Tablero tablero) {
        int deltaFila = destino.getFila() - posicion.getFila();
        int deltaColumna = destino.getColumna() - posicion.getColumna();

        Casilla casillaObjetivo = tablero.obtenerCasilla(destino);
        if (casillaObjetivo.estaOcupada()) {
            Unidad vek = casillaObjetivo.getUnidad();
            vek.recibirDaño(2); // El puño causa 2 de daño[cite: 6]
            casillaObjetivo.liberar();

            // Empuja 1 casilla en la dirección del golpe
            Posicion posEmpujado = new Posicion(destino.getFila() + deltaFila, destino.getColumna() + deltaColumna);
            Casilla casillaEmpuje = tablero.obtenerCasilla(posEmpujado);
            casillaEmpuje.ocupar(vek);
        }
    }
}