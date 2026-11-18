package edu.fiuba.algo3.modelo;

public class FabricaFase {

    private final Tablero tablero;
    private final GestorEmergencia gestor;

    public FabricaFase(Tablero tablero, GestorEmergencia gestor) {
        this.tablero = tablero;
        this.gestor = gestor;
    }

    public Fase crearFaseVek() {
        return new FaseVek(tablero);
    }

    public Fase crearFaseJugador() {
        return new FaseJugador(tablero);
    }

    public Fase crearFaseResolucion() {
        return new FaseResolucion(tablero);
    }

    public Fase crearFaseEmergencia() {
        return new FaseEmergencia(tablero, gestor.generarEmergencia());
    }
}
