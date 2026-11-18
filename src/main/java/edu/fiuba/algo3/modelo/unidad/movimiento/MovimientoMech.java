package edu.fiuba.algo3.modelo.unidad.movimiento;

import edu.fiuba.algo3.modelo.errores.casillas.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.errores.unidad.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.errores.unidad.MovimientoFueraDeRangoException;
import edu.fiuba.algo3.modelo.errores.unidad.PosicionFueraDelTableroException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.casilla.Casilla;
import edu.fiuba.algo3.modelo.tablero.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.unidad.Unidad;

import java.util.EnumSet;
import java.util.Set;

public class MovimientoMech implements Movible {

    private static final int RANGO_MOVIMIENTO = 3;
    public static final int FILAS = 8;
    public static final int COLUMNAS = 8;

    private static final Set<TipoCasilla> TRANSITABLES =
            EnumSet.of(
                    TipoCasilla.SUELO,
                    TipoCasilla.AGUA,
                    TipoCasilla.ACIDO
            );

    @Override
    public void mover(Unidad unidad, Posicion posicionFinal, Tablero tablero) {

        if (!posicionFinal.estaEnElTablero(FILAS, COLUMNAS)) {
            throw new PosicionFueraDelTableroException();
        }

        Casilla casillaDestino = tablero.obtenerCasilla(posicionFinal);
        TipoCasilla tipoCasillaDestino = casillaDestino.getTipo();

        if (!TRANSITABLES.contains(tipoCasillaDestino)) {
            throw new CasillaNoTransitableException();
        }

        if (casillaDestino.estaOcupada()) {
            throw new MovimientoEnPosicionOcupadaException();
        }

        if (unidad.getPosicion().distanciaA(posicionFinal) > RANGO_MOVIMIENTO) {
            throw new MovimientoFueraDeRangoException("Movimiento fuera del rango de la unidad");
        }

        Posicion posicionAnterior = unidad.getPosicion();

        tablero.obtenerCasilla(posicionAnterior).liberar();
        casillaDestino.ocupar(unidad);
        unidad.actualizarPosicion(posicionFinal);

    }

}
