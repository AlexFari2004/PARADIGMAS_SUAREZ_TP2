package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.ataque.AtaqueAdyacente;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.movimiento.MovimientoVekTerrestre;


public class VekSaltador extends Vek {
    private static final int VIDA_INICIAL = 2;
    private static final int RANGO_MOVIMIENTO = 3;

    public VekSaltador() {
        super(2, new MovimientoVekTerrestre(3), new AtaqueAdyacente(), 2, new SinEfecto());
    }
}
