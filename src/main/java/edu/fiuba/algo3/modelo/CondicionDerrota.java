package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

public class CondicionDerrota implements Verificable {

    public CondicionDerrota() {    }
    public boolean verificarResultado(RedElectrica redElectrico) {
        return redElectrico.estaAgotada();
    }


}
