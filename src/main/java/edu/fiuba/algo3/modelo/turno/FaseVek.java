package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.ataque.AtaqueRegistrado;
import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekIA;

public class FaseVek extends Fase {
    private final Tablero tablero;
    private final VekIA vekIA;
    private final AtaqueRegistrado registrado;
    private final GestorEmergencia gestorEmergencia;

    public FaseVek(Tablero tablero, VekIA vekIA,
                   AtaqueRegistrado registrado,
                   GestorEmergencia gestorEmergencia) {
        this.tablero = tablero;
        this.vekIA = vekIA;
        this.registrado = registrado;
        this.gestorEmergencia = gestorEmergencia;
    }

    @Override
    public Fase avanzar(FabricaFase fabrica, ControladorTurno controlador) {
        return fabrica.crearFaseJugador();
    }


    @Override
    public void ejecutar() {
        registrado.limpiar();
        for (Vek vek : tablero.obtenerVeks()) {
            vekIA.jugarTurno(vek, tablero, registrado);
        }
        gestorEmergencia.marcarProximaEmergencia();
        finalizada = true;
    }
}
