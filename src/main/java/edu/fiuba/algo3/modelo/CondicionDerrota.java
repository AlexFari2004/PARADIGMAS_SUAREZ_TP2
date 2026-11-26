package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import java.util.List;

public class CondicionDerrota implements Verificable {

    public CondicionDerrota() {    }
    @Override
    public boolean verificarResultado(Tablero tablero) {
        return tablero.redElectricaAgotada() || tablero.obtenerMechsVivos().isEmpty();
    }


}
