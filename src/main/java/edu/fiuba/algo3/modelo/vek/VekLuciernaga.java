package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.ataque.AtaqueAdyacente;
import edu.fiuba.algo3.modelo.ataque.AtaqueRectilineo;
import edu.fiuba.algo3.modelo.ataque.EfectoContaminacion;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.movimiento.MovimientoAvispon;
import edu.fiuba.algo3.modelo.movimiento.MovimientoVekTerrestre;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;


import java.util.List;

public class VekLuciernaga extends Vek {
    private static final int VIDA_INICIAL = 3;
    private static final int RANGO_MOVIMIENTO = 2;

    public VekLuciernaga() {
        super(3, new MovimientoVekTerrestre(2), new AtaqueRectilineo(), 1, new EfectoContaminacion());
    }

}
