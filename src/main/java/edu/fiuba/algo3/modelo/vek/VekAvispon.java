package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.ataque.AtaqueAdyacente;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.movimiento.MovimientoAvispon;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;


import java.util.List;

public class VekAvispon extends Vek {

    public VekAvispon() {
        super(2, new MovimientoAvispon(4), new AtaqueAdyacente(), 1, new SinEfecto());
    }
}
