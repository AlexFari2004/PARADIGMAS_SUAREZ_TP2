package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class VekSaltador extends Vek{

    public VekSaltador() { super(2, 2,new SinEfecto(), new MovimientoSaltador()); }

    @Override
    protected List<Posicion> posicionesAtacables(Posicion actual, Tablero tablero) {
        return tablero.obtenerPosicionesOrtogonales(actual);
    }
}
