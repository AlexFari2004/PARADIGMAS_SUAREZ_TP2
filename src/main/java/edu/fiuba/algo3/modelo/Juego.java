package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataque.AtaqueRegistrado;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.*;
import edu.fiuba.algo3.modelo.vek.CriterioMenorFila;
import edu.fiuba.algo3.modelo.vek.VekIA;

import java.util.List;

public class Juego {
    private static final int CANTIDAD_MECHS = 3;
    private static final int TURNOS_MISION = 5;

    private final RedElectrica redElectrica;
    private final ControladorTurno controladorTurno;
    private final Tablero tablero;
    private final CondicionVictoria condicionVictoria;
    private final CondicionDerrota condicionDerrota;

    public Juego(List<Mech> mechsElegidos, List<Posicion> posicionesIniciales,
                 List<Emergencia> emergenciasPredefinidas) {
        if (mechsElegidos == null || mechsElegidos.size() != CANTIDAD_MECHS
                || posicionesIniciales == null || posicionesIniciales.size() != CANTIDAD_MECHS) {
            throw new IllegalArgumentException(
                    "Se requieren exactamente 3 Mechs y 3 posiciones iniciales.");
        }

        this.redElectrica = new RedElectrica();
        this.tablero = new Tablero(redElectrica);
        this.condicionVictoria = new CondicionVictoria(TURNOS_MISION, redElectrica);
        this.condicionDerrota = new CondicionDerrota(redElectrica);

        for (int i = 0; i < CANTIDAD_MECHS; i++) {
            tablero.colocar(mechsElegidos.get(i), posicionesIniciales.get(i));
        }

        VekIA vekIA = new VekIA(new CriterioMenorFila());
        AtaqueRegistrado registrado = new AtaqueRegistrado();
        GestorEmergencia gestorEmergencia = new GestorEmergencia(emergenciasPredefinidas);
        FabricaFase fabricaFase = new FabricaFase(tablero, gestorEmergencia, registrado, vekIA);

        this.controladorTurno = new ControladorTurno(fabricaFase, condicionVictoria);
    }

    public Tablero obtenerTablero() {
        return tablero;
    }

    public RedElectrica obtenerRedElectrica() {
        return redElectrica;
    }

    public Fase obtenerFaseActual() {
        return controladorTurno.obtenerFaseActual();
    }

    public int obtenerTurnoActual() {
        return controladorTurno.obtenerTurnoActual();
    }

    public void avanzarFase() {
        if (terminoPartida()) {
            return;
        }
        controladorTurno.ejecutarFaseActual();
    }

    public boolean gano() {
        return !condicionDerrota.verificarResultado() && condicionVictoria.verificarResultado();
    }

    public boolean perdio() {
        return condicionDerrota.verificarResultado();
    }

    public boolean terminoPartida() {
        return gano() || perdio();
    }

}
