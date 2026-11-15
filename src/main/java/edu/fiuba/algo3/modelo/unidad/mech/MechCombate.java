package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.mock.TableroStub;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;

public class MechCombate extends Mech {
    private final Puno puno;

    public MechCombate(Puno puno) {
        this.puno = puno;
        this.vida = 3; // El Mech de combate tiene 3 puntos de vida
    }

    public void atacar(Posicion destino, TableroStub tablero) {
        int deltaFila = destino.obtenerFila() - posicion.obtenerFila();
        int deltaColumna = destino.obtenerColumna() - posicion.obtenerColumna();

        Casilla casillaObjetivo = tablero.obtenerCasilla(destino);
        if (casillaObjetivo.estaOcupado()) {
            Unidad vek = casillaObjetivo.obtenerUnidad();
            vek.recibirDano(2); // El puño causa 2 de daño[cite: 6]
            casillaObjetivo.liberar();

            // Empuja 1 casilla en la dirección del golpe
            Posicion posEmpujado = new Posicion(destino.obtenerFila() + deltaFila, destino.obtenerColumna() + deltaColumna);
            Casilla casillaEmpuje = tablero.obtenerCasilla(posEmpujado);
            casillaEmpuje.colocarUnidad(vek);
        }
    }
}