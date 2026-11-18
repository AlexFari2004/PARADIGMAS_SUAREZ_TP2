package edu.fiuba.algo3.modelo.unidad.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.efecto.SinEfecto;
import edu.fiuba.algo3.modelo.unidad.movimiento.MovimientoSaltador;

import java.util.List;

public class VekSaltador extends Vek {

    public VekSaltador() {
        super(2, 2, new SinEfecto(), new MovimientoSaltador());
    }

    @Override
    protected List<Posicion> posicionesAtacables(Posicion actual, Tablero tablero) {
        return tablero.obtenerPosicionesOrtogonales(actual);
    }
}
