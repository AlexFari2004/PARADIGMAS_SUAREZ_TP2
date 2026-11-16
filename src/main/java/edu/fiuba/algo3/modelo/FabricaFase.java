package edu.fiuba.algo3.modelo;

import java.util.List;

public class FabricaFase {

    public FaseVek crearFaseVek() {
        return new FaseVek();
    }

    public FaseResolucion crearFaseResolucion() {
        return new FaseResolucion();
    }

    public FaseJugador crearFaseJugador() {
        return new FaseJugador();
    }

    public FaseEmergencia crearFaseEmergencia() {
        return new FaseEmergencia();
    }
}
