package edu.fiuba.algo3.modelo.turno.fases;


import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.vek.Vek;

public class FaseResolucion extends Fase {
    private final Tablero tablero;

    public FaseResolucion(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void ejecutar() {
        //falta el de jugador
        for (Vek vek : tablero.obtenerVeks()) {   // si muere, no ataca
            vek.resolverAtaque(tablero);
        }
        finalizada = true;
    }

    @Override
    public Fase avanzar(FabricaFase fabrica) {
        return fabrica.crearFaseEmergencia();
    }
}