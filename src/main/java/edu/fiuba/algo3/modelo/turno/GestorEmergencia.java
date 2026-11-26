package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.VekAvispon;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class GestorEmergencia {
    private final Queue<Emergencia> colaEmergencias;
    private Emergencia emergenciaActual;

    public GestorEmergencia(List<Emergencia> emergenciasPredefinidas) {
        this.colaEmergencias = new ArrayDeque<>(emergenciasPredefinidas);
    }


    public void marcarProximaEmergencia() {
        this.emergenciaActual = colaEmergencias.poll();
    }

    public Emergencia obtenerEmergenciaActual() {
        return emergenciaActual;
    }

    public void resolverEmergencia(Tablero tablero) {
        if (emergenciaActual == null) {
            return;
        }
        emergenciaActual.emerger(tablero);
        emergenciaActual = null;
    }
}
