package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

public class CondicionVictoria implements Verificable{
    private final int totalTurnosMision;
    private int turnosCompletados;
    private final RedElectrica redElectrica;

    public CondicionVictoria(int totalTurnosMision, RedElectrica redElectrica) {
        this.redElectrica = redElectrica;
        this.totalTurnosMision = totalTurnosMision;
    }

    public void registrarTurnoCompletado() {
        this.turnosCompletados++;
    }

    @Override
    public boolean verificarResultado() {
        return !redElectrica.estaAgotada() && turnosCompletados >= totalTurnosMision;
    }

}
