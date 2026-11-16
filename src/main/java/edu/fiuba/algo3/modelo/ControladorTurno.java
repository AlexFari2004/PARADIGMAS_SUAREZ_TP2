package edu.fiuba.algo3.modelo;

import java.util.List;

public class ControladorTurno {

    private final Tablero tablero;
    private final RedElectrica redElectrica;
    private final List<Ataque> ataques;
    private final List<Emergencia> emergencias;
    private final int turnoRestante;
    private int turnoActual = 1;
    private final FabricaFase fabrica;
    private Fase faseActual;
    public ControladorTurno(Tablero tablero, RedElectrica redElectrica,FabricaFase fabrica ,int turnoRestante) {
        this.tablero = tablero;
        this.fabrica = fabrica;
        this.redElectrica = redElectrica;
        this.turnoRestante = turnoRestante;
        this.faseActual = fabrica.crearFaseVek();
    }

    public Fase iniciarTurno(FabricaFase fabricaFase) {};

    public void ejecutarFaseActual() {

        faseActual.ejecutar();

        faseActual = faseActual.avanzar(fabrica);

        turnoActual++;

    }
    public void finalizarTurnos(){
        turnoRestante = turnoRestante - 1;
    }
}
