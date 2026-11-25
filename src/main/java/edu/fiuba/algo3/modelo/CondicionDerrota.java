package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

public class CondicionDerrota implements Verificable {
    private final RedElectrica redElectrica;

    public CondicionDerrota(RedElectrica redElectrica) {
        this.redElectrica = redElectrica;
    }
    @Override
    public boolean verificarResultado() {
        return redElectrica.estaAgotada();
    }


}
