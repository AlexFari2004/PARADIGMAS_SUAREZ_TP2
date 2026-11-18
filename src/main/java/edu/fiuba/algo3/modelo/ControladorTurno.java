package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.fases.FabricaFase;
import edu.fiuba.algo3.modelo.fases.Fase;

import java.util.ArrayList;
import java.util.List;

public class ControladorTurno {

    private final Tablero tablero;
    private final RedElectrica redElectrica;
    private final List<Ataque> ataques = new ArrayList<>();
    private final List<Emergencia> emergencias = new ArrayList<>();
    private int turnoRestante;
    private int turnoActual = 1;
    private final FabricaFase fabrica;
    private Fase faseActual;

    public ControladorTurno(Tablero tablero, RedElectrica redElectrica, FabricaFase fabrica, int turnoRestante) {
        this.tablero = tablero;
        this.fabrica = fabrica;
        this.redElectrica = redElectrica;
        this.turnoRestante = turnoRestante;
        this.faseActual = fabrica.crearFaseVek();
    }

    public Fase iniciarTurno(FabricaFase fabricaFase) {
        faseActual = fabricaFase.crearFaseVek();
        return faseActual;
    }

    ;

    public void ejecutarFaseActual() {

        faseActual.ejecutar();

        faseActual = faseActual.avanzar(fabrica);

        turnoActual++;

    }

    public void finalizarTurnos() {
        turnoRestante = turnoRestante - 1;
    }
}
