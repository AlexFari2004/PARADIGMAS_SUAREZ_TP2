package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidad.Vek;

public class FaseVek extends Fase{
    private final Tablero tablero;

    public FaseVek(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public Fase avanzar(FabricaFase fabrica){
        return fabrica.crearFaseJugador();
    };

    @Override
    public void ejecutar() {
        for (Vek vek : tablero.obtenerVeks()) {
            vek.planificarAccion(tablero);
        }
        finalizada = true;
    }
}
