package edu.fiuba.algo3.modelo.turno.fases;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.Emergencia;

public class FaseEmergencia extends Fase {
    private final Emergencia emergencia;
    private final Tablero tablero;

    public FaseEmergencia(Tablero tablero, Emergencia emergencia) {
        this.tablero = tablero;
        this.emergencia = emergencia;
    }

    @Override
    public void ejecutar() {
        emergencia.emerger(tablero);
    }

    @Override
    public Fase avanzar(FabricaFase fabrica) {
        return fabrica.crearFaseVek();
    }

}