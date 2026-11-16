package edu.fiuba.algo3.modelo;

public class FaseVek extends Fase{
    private Emergencia emergencia;
    private Tablero tablero;

    public FaseVek(Tablero tablero, Emergencia emergencia) {
        this.tablero = tablero;
        this.emergencia = emergencia;
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
        emergencia.marcarProxima(tablero);
        finalizada = true;
    }
}
