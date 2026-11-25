package edu.fiuba.algo3.modelo.turno;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.Mech;

import java.util.List;


public class FaseJugador extends Fase {
    private final Tablero tablero;
    private boolean deshacerUsadoEnTurno = false;
    private Mech ultimoMechMovido;

    public FaseJugador(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void ejecutar() {
        // Nada automático: espera las acciones del jugador.
    }

    public void mover(Mech mech, Posicion destino) {
        mech.mover(destino, tablero);
        this.ultimoMechMovido = mech;
    }

    public void atacar(Mech mech, Posicion objetivo) {
        mech.atacar(objetivo, tablero);
    }

    public void finalizarTurno() {
        finalizada = true;
    }

    public void deshacer() {
        if (deshacerUsadoEnTurno || ultimoMechMovido == null) {
            throw new IllegalStateException("No se puede deshacer el movimiento en este turno.");
        }
        ultimoMechMovido.deshacerMovimiento(tablero);
        deshacerUsadoEnTurno = true;
        ultimoMechMovido = null;
    }


    @Override
    public Fase avanzar(FabricaFase fabricaFase, ControladorTurno controlador) {
        return fabricaFase.crearFaseResolucion();
    }

}
