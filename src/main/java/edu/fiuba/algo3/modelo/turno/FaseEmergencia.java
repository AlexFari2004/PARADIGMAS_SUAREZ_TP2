package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.Mech;

public class FaseEmergencia extends Fase {
    private final GestorEmergencia gestor;
    private final Tablero tablero;

    public FaseEmergencia(Tablero tablero, GestorEmergencia gestor) {
        this.tablero = tablero;
        this.gestor = gestor;
    }

    @Override
    public void ejecutar() {
        gestor.resolverEmergencia(tablero);
        finalizada = true;
    }

    @Override
    public Fase avanzar(FabricaFase fabrica, ControladorTurno controlador) {
        for (Mech mech : tablero.obtenerMechsVivos()) {
            mech.reestablecerEstado();
        }
        controlador.completarTurno();
        return fabrica.crearFaseVek();
    }

}