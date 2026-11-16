package edu.fiuba.algo3.modelo;

public class FaseEmergencia extends Fase {
    private final Emergencia emergencia;
    private final Tablero tablero;

    public FaseEmergencia(Tablero tablero, Emergencia emergencia) {
        this.tablero = tablero;
        this.emergencia = emergencia;
    }

    @Override
    public void ejecutar() {
        emergencia.aplicarEn(tablero);
        finalizada = true;
    }

    @Override
    public Fase avanzarFase(FabricaFase fabrica) { return fabrica.crearVek(); }

}