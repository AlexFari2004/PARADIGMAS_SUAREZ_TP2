package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.mech.Direccion;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoEmpuje implements EfectoAtaque{

    @Override
    public void aplicarEfecto(Posicion atacante, Posicion impacto, Tablero tablero) {
        Direccion dir = Direccion.entre(atacante, impacto);
        tablero.obtenerCasilla(impacto).getOcupante().aplicarEmpuje(this, impacto, dir, tablero);
    }

    // Lo invoca solo Unidad (doble despacho): edificios y montañas no llegan aquí
    public void empujarUnidad(Unidad unidad, Posicion origen, Direccion dir, Tablero tablero) {
        Posicion destino = dir.desde(origen);

        if (!destino.estaEnElTablero(destino.getFila(), destino.getColumna())) {
            return;
        }
        Casilla casillaDestino = tablero.obtenerCasilla(destino);

        if (casillaDestino.bloqueaMovimiento()) {
            unidad.recibirDanio(1);
            casillaDestino.recibirDanio(1);
            return;
        }
        tablero.colocar(unidad,destino);
    }

}
