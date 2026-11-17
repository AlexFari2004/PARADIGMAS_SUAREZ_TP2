package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class VekLuciernaga extends Vek{

    public VekLuciernaga(){
        super(3,1,new EfectoAcido(),new MovimientoLuciernaga());
    }
    @Override
    protected List<Posicion> posicionesAtacables(Posicion actual, Tablero tablero) {
        return tablero.obtenerPosicionesOrtogonales(actual);
    }
}
