package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.ControladorTurno;

public class Juego {
    private ControladorTurno controladorTurno;
    private Tablero tablero;

    public Juego(ControladorTurno controladorTurno, Tablero tablero) {
        this.controladorTurno = controladorTurno;
        this.tablero = tablero;
    }

}
