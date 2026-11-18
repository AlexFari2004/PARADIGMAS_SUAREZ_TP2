package edu.fiuba.algo3.modelo.fases;

public abstract class Fase {

    protected boolean finalizada = false;

    public abstract void ejecutar();

    public boolean estaFinalizada() {
        return finalizada;
    }

    public abstract Fase avanzar(FabricaFase fabrica);
}
