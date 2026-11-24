package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.efecto.SinEfecto;
import edu.fiuba.algo3.modelo.unidad.movilidad.Voladora;
import edu.fiuba.algo3.modelo.unidad.movimiento.MovimientoAvispon;

import java.util.List;

public class VekAvispon extends Vek {

    public VekAvispon() {
        super(2, 1, new edu.fiuba.algo3.modelo.vek.SinEfecto(), new edu.fiuba.algo3.modelo.vek.MovimientoAvispon(), new Voladora());
    }

    @Override
    protected List<Posicion> posicionesAtacables(Posicion actual, Tablero tablero) {
        return tablero.obtenerPosicionesOrtogonales(actual);
    }

}
