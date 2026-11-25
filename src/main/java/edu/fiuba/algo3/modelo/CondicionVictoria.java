package edu.fiuba.algo3.modelo;

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
    public boolean verificarResultado(RedElectrica redElectrico) {
        return !redElectrico.estaAgotada() && turnosCompletados >= totalTurnosMision;
    }

}
