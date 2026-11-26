package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.Tablero;

public class CondicionVictoria implements Verificable{
    private final int totalTurnosMision;
    private int turnosCompletados;

    public CondicionVictoria(int totalTurnosMision) {
        this.totalTurnosMision = totalTurnosMision;
    }

    public void registrarTurnoCompletado() {
        this.turnosCompletados++;
    }

    @Override
    public boolean verificarResultado(Tablero tablero) {
        return !tablero.redElectricaAgotada() && turnosCompletados >= totalTurnosMision;
    }

}
