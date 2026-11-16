package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class FaseResolucion extends Fase{
    private Tablero tablero;
    public FaseResolucion(Tablero tablero) { this.tablero = tablero; }

    @Override
    public Fase avanzarFase(FabricaFase fabrica) {
        return fabrica.crearFaseEmergencia();
    }
    @Override
    public void ejecutar() {
        for (Vek vek : new ArrayList<>(tablero.obtenerVeks())) {
            vek.resolverAtaque(tablero);
        }
        finalizada = true;
    }
}
