package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

public class CondicionDerrota implements Verificable {
    private final Tablero tablero;
    private final RedElectrica redElectrica;

    public CondicionDerrota(Tablero tablero, RedElectrica redElectrica) {
        this.tablero = tablero;
        this.redElectrica = redElectrica;
    }
    @Override
    public boolean verificarResultado() {
        return redElectrica.energiaDisponible() || tablero.obtenerMechsVivos().isEmpty();
    }


}
