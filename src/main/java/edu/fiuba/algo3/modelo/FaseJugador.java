package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.unidad.Mech;

import java.util.HashMap;

public class FaseJugador extends Fase {
    private final Map<Mech, MovimientoMech> movimientos = new HashMap<>();
    private boolean deshacerAccion = false;
    private Tablero tablero;
    public FaseJugador(Tablero tablero) {
        this.tablero = tablero;
        // obtener los mech vivos
    }

    public void moverMechA(Mech mech, Posicion destino) {
        MovimientoMech mov = mech.mover(tablero, destino);   // lanza excepcion si ya se movio
        movimientos.put(mech, mov);
    }

    public void atacarCon(Mech mech, Posicion objetivo) {
        mech.atacarA(tablero, objetivo);   // daño inmediato
        // lanza excepcion si ya ataco
    }

    @Override
    public Fase avanzar(FabricaFase fabricaFase) {
        return fabricaFase.crearFaseResolucion();
    };

}
