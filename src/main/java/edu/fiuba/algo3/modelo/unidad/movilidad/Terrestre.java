package edu.fiuba.algo3.modelo.unidad.movilidad;

import edu.fiuba.algo3.modelo.unidad.Unidad;

public class Terrestre implements Movilidad {

    @Override
    public void caerAlAgua(Unidad unidad){
        unidad.hundirse();
    }

    @Override
    public void pisarAcido(Unidad unidad){
        unidad.contaminar();
    }
}
