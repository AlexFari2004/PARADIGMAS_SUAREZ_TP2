package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidad.Mech;
import java.util.List;


public class FaseJugador extends Fase {
    private boolean deshacerAccion = false;
    private Tablero tablero;
    private List<Mech> mechDisponible;
    public FaseJugador(Tablero tablero) {
        this.tablero = tablero;
        mechDisponible = tablero.obtenerMechsVivos();
    }

    @Override
    public void ejecutar() {
        // Nada automático: espera las acciones del jugador.
    }

    public void mover(Mech mech,Posicion destino, Tablero tablero) {
        //
    }

    public void atacar(Mech mech, Posicion objetivo) {
        //
    }

    public void finalizarTurno() {
        finalizada = true;
    }
    public void deshacer() {
        //
    }


    @Override
    public Fase avanzar(FabricaFase fabricaFase) {
        return fabricaFase.crearFaseResolucion();
    };

}
