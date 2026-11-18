package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.movilidad.Voladora;

import java.util.List;

public class VekAvispon extends Vek {

    public VekAvispon() {
        super(2, 1, new SinEfecto(), new MovimientoAvispon(), new Voladora());
    }

    @Override
    protected List<Posicion> posicionesAtacables(Posicion actual, Tablero tablero) {
        return tablero.obtenerPosicionesOrtogonales(actual);
    }

}
