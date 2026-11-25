package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.errores.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.errores.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.errores.MovimientoFueraDeRangoException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.Mech;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MechMovimientoTest {
    private static final Posicion ORIGEN = new Posicion(4, 3);

    private Tablero tablero;
    private MechPrueba mech1;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
        mech1 = new MechPrueba(3);
        tablero.colocar(mech1, ORIGEN);
    }

    @Test
    public void mechNoPuedeMoverseEnPosicionFueraDeRango() {
        Posicion fueraDeRango = new Posicion(5, 7);

        assertThrows(MovimientoFueraDeRangoException.class,
                () -> mech1.mover(fueraDeRango, tablero));
        assertEquals(ORIGEN, mech1.obtenerPosicion());
    }

    @Test
    public void mechNoPuedeMoverseEnPosicionConOtraUnidad() {
        Mech mech2 = new MechPrueba(3);
        Posicion posicionMech2 = new Posicion(4, 4);
        tablero.colocar(mech2, posicionMech2);

        assertThrows(MovimientoEnPosicionOcupadaException.class,
                () -> mech1.mover(posicionMech2, tablero));
        assertEquals(ORIGEN, mech1.obtenerPosicion());
    }

    @Test
    public void mechNoPuedeMoverseEnPosicionConObstaculo() {
        Posicion posicionConObstaculo = new Posicion(5, 3);

        assertThrows(CasillaNoTransitableException.class,
                () -> mech1.mover(posicionConObstaculo, tablero));
        assertEquals(ORIGEN, mech1.obtenerPosicion());
    }

    @Test
    public void mechPuedeMoverseEnPosicionValida() {
        Posicion posicionValida = new Posicion(3, 3);

        assertDoesNotThrow(() -> mech1.mover(posicionValida, tablero));
        assertEquals(posicionValida, mech1.obtenerPosicion());
    }
}