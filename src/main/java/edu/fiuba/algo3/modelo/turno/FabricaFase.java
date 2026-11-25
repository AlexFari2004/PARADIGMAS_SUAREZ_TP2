package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.ataque.AtaqueRegistrado;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.VekIA;

public class FabricaFase {

    private final Tablero tablero;
    private final GestorEmergencia gestor;
    private final VekIA vekIA;
    private final AtaqueRegistrado registrado;

    public FabricaFase(Tablero tablero, GestorEmergencia gestor, AtaqueRegistrado registrado, VekIA vekIA) {
        this.tablero = tablero;
        this.gestor = gestor;
        this.registrado = registrado;
        this.vekIA = vekIA;
    }

    public Fase crearFaseVek() {
        return new FaseVek(tablero, vekIA, registrado,gestor);
    }

    public Fase crearFaseJugador() {

        return new FaseJugador(tablero);
    }

    public Fase crearFaseResolucion() {

        return new FaseResolucion(tablero, registrado);
    }

    public Fase crearFaseEmergencia() {
        return new FaseEmergencia(tablero, gestor);
    }
}
