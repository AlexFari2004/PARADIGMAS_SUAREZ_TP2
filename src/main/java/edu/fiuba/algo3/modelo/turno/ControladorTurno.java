package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.CondicionVictoria;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.ataque.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class ControladorTurno {

    private static final int TURNOS_MISION = 5;

    private final FabricaFase fabrica;
    private final CondicionVictoria condicionVictoria;
    private Fase faseActual;
    private int turnosCompletados = 0;

    public ControladorTurno(FabricaFase fabrica, CondicionVictoria condicionVictoria) {
        this.fabrica = fabrica;
        this.condicionVictoria = condicionVictoria;
        this.faseActual = fabrica.crearFaseVek();
    }

    public Fase obtenerFaseActual() {
        return faseActual;
    }

    public int obtenerTurnoActual() {
        return Math.min(turnosCompletados + 1, TURNOS_MISION);
    }

    public void ejecutarFaseActual() {
        faseActual.ejecutar();
        if (faseActual.estaFinalizada()) {
            faseActual = faseActual.avanzar(fabrica, this);
        }
    }

    public void completarTurno() {
        turnosCompletados++;
        condicionVictoria.registrarTurnoCompletado();
    }
}
