package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

public class CondicionVictoria implements Verificable{
    private final Tablero tablero;
    private int turnoPendiente;
    private final RedElectrica redElectrica;

    public CondicionVictoria(Tablero tablero, int turnoPendiente, RedElectrica redElectrica) {
        this.tablero = tablero;
        this.turnoPendiente = turnoPendiente;
        this.redElectrica = redElectrica;
    }
    @Override
    public boolean verificarResultado() {
        return tablero.obtenerVeks().isEmpty() && turnoPendiente > 0 && (redElectrica.energiaDisponible());
    }

}
