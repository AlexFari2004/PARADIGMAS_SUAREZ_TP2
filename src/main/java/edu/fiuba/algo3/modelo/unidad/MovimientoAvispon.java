package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.casilla.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.errors.MovimientoFueraDeRangoException;

import java.util.EnumSet;

public class MovimientoAvispon extends MovimientoVek{
    public MovimientoAvispon() {
        super(4, EnumSet.of(TipoCasilla.SUELO, TipoCasilla.AGUA, TipoCasilla.ACIDO));
    }

    @Override
    protected boolean puedeLlegar(Unidad unidad, Posicion destino, Tablero tablero) {
        // vuela: solo importa la distancia
        return unidad.getPosicion().distanciaA(destino) <= this.rangoMovimiento;
    }

    @Override
    public void mover(Unidad unidad, Posicion destino, Tablero tablero) {
        validadDestino(destino, tablero);
        if (!puedeLlegar(unidad, destino, tablero))
            throw new MovimientoFueraDeRangoException("Movimiento fuera del rango de la unidad");
        desplazar(unidad, destino, tablero);
    }


}
