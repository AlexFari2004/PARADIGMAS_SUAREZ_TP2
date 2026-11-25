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

    // Llamado en Fase 1: marca cuál es la próxima emergencia
    public void marcarProximaEmergencia() {
        this.emergenciaActual = colaEmergencias.poll();
    }

    public Emergencia obtenerEmergenciaActual() {
        return emergenciaActual;
    }

    // Llamado en Fase 4: ejecuta la emergencia marcada
    public void resolverEmergencia(Tablero tablero) {
        if (emergenciaActual == null) {
            return;
        }
        emergenciaActual.emerger(tablero);
        emergenciaActual = null;
    }
}
