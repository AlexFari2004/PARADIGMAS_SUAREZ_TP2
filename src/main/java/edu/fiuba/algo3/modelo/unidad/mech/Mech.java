package edu.fiuba.algo3.modelo.unidad.mech;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.unidad.Unidad;
import edu.fiuba.algo3.modelo.unidad.errors.LimiteDeAccionesAlcanzadaException;
import edu.fiuba.algo3.modelo.unidad.errors.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.unidad.errors.MovimientoFueraDeRangoException;

public class Mech extends Unidad {
    private Posicion posicionAnterior;
    private boolean yaMovio = false;
    private boolean yaAtaco = false;
    private boolean yaDeshizoMovimiento = false;

    public void moverseA(Posicion destino, Tablero tablero) {
        if (yaMovio) {
            throw new LimiteDeAccionesAlcanzadaException("El mech ya se movió en este turno.");
        }

        if (posicion.calcularDistanciaManhattan(destino) > 3) {
            throw new MovimientoFueraDeRangoException("La posición excede el rango máximo de movimiento.");
        }

        Casilla casillaDestino = tablero.obtenerCasilla(destino);
        if (casillaDestino.estaOcupado() || casillaDestino.esObstaculo()) {
            throw new MovimientoEnPosicionOcupadaException("La casilla está ocupada o contiene un obstáculo.");
        }

        Casilla casillaActual = tablero.obtenerCasilla(this.posicion);
        casillaActual.liberar();

        this.posicionAnterior = this.posicion;
        this.posicion = destino;
        casillaDestino.colocarUnidad(this);
        this.yaMovio = true;
    }

    public void atacar(Posicion posicionAtaque, Tablero tablero) {
        if (yaAtaco) {
            throw new LimiteDeAccionesAlcanzadaException("El mech ya realizó un ataque en este turno.");
        }
        this.yaAtaco = true;
    }

    public void deshacerMovimiento(Tablero tablero) {
        if (!yaMovio || yaAtaco || yaDeshizoMovimiento) {
            throw new LimiteDeAccionesAlcanzadaException("No es posible deshacer el movimiento.");
        }

        Casilla casillaActual = tablero.obtenerCasilla(this.posicion);
        casillaActual.liberar();

        Casilla casillaAnterior = tablero.obtenerCasilla(this.posicionAnterior);
        casillaAnterior.colocarUnidad(this);

        this.posicion = this.posicionAnterior;
        this.yaDeshizoMovimiento = true;
        this.yaMovio = false;
    }
}