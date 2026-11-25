package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.errores.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.errores.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.errores.MovimientoFueraDeRangoException;
import edu.fiuba.algo3.modelo.errores.PosicionFueraDelTableroException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.movimiento.MovimientoMech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UnidadMovimientoTest {

    private static final Posicion ORIGEN = new Posicion(4, 3);

    private Tablero tablero;
    private UnidadDePrueba unidad;

    private static class UnidadDePrueba extends Unidad {

        public UnidadDePrueba() {
            super(3, new MovimientoMech());
        }

        public void registrarEn(Tablero tablero) {
        }

        ;
    }

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
        unidad = new UnidadDePrueba();
        tablero.colocar(unidad, ORIGEN);
    }

    @Test
    public void unidadNoPuedeMoverseFueraDeRango() {

        Posicion fueraDeRango = new Posicion(5, 7);

        assertThrows(
                MovimientoFueraDeRangoException.class,
                () -> unidad.mover(fueraDeRango, tablero)
        );

        assertEquals(ORIGEN, unidad.obtenerPosicion());
    }

    @Test
    public void unidadNoPuedeMoverseAUnaPosicionOcupadaPorOtraUnidad() {

        UnidadDePrueba otraUnidad = new UnidadDePrueba();
        Posicion posicionOtraUnidad = new Posicion(4, 4);
        tablero.colocar(otraUnidad, posicionOtraUnidad);

        assertThrows(
                MovimientoEnPosicionOcupadaException.class,
                () -> unidad.mover(posicionOtraUnidad, tablero)
        );

        assertEquals(ORIGEN, unidad.obtenerPosicion());
    }

    @Test
    public void unidadNoPuedeMoverseAUnaPosicionConUnTerrenoQueNoPuedeTransitar() {

        Posicion posicionConObstaculo = new Posicion(5, 3);

        assertThrows(
                CasillaNoTransitableException.class,
                () -> unidad.mover(posicionConObstaculo, tablero)
        );

        assertEquals(ORIGEN, unidad.obtenerPosicion());
    }

    @Test
    public void unidadPuedeMoverseAUnaPosicionValida() {

        Posicion posicionValida = new Posicion(3, 3);

        unidad.mover(posicionValida, tablero);

        assertEquals(posicionValida, unidad.obtenerPosicion());
        assertFalse(tablero.obtenerCasilla(ORIGEN).estaOcupada());
        assertTrue(tablero.obtenerCasilla(posicionValida).estaOcupada());
    }

    @Test
    public void unidadNoPuedeMoverseFueraDelTablero() {

        Posicion posicionFueraDelTablero = new Posicion(8, 3);

        assertThrows(
                PosicionFueraDelTableroException.class,
                () -> unidad.mover(posicionFueraDelTablero, tablero)
        );
        assertEquals(ORIGEN, unidad.obtenerPosicion());
    }
}