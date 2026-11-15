package edu.fiuba.algo3.entrega_1.unidades;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.errors.MovimientoFueraDeRangoException;
import edu.fiuba.algo3.modelo.unidad.Mech;
import edu.fiuba.algo3.modelo.unidad.errors.MovimientoEnPosicionOcupadaException;


public class MechMovimientoTest {
    private static final Posicion ORIGEN = new Posicion(4, 3);

    private Tablero tablero;
    private MechPrueba mech1;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
        mech1 = new MechPrueba();
        tablero.colocar(mech1, ORIGEN);
    }

    @Test
    public void mechNoPuedeMoverseEnPosicionFueraDeRango() {
        Posicion fueraDeRango = new Posicion(5, 7);

        assertThrows(MovimientoFueraDeRangoException.class,
            () -> mech1.moverseA(fueraDeRango, tablero));
        assertEquals(ORIGEN, mech1.obtenerPosicion());
    }

    @Test
    public void mechNoPuedeMoverseEnPosicionConOtraUnidad() {
        Mech mech2 = new MechPrueba();
        Posicion posicionMech2 = new Posicion(4, 4);
        tablero.colocar(mech2, posicionMech2);

        assertThrows(MovimientoEnPosicionOcupadaException.class,
            () -> mech1.moverseA(posicionMech2, tablero));
        assertEquals(ORIGEN, mech1.obtenerPosicion());
    }

    @Test
    public void mechNoPuedeMoverseEnPosicionConObstaculo() {
        Posicion posicionConObstaculo = new Posicion(5, 3);
        
        assertThrows(MovimientoEnPosicionOcupadaException.class,
            () -> mech1.moverseA(posicionConObstaculo, tablero));
        assertEquals(ORIGEN, mech1.obtenerPosicion());
    }

    @Test
    public void mechPuedeMoverseEnPosicionValida() {
        Posicion posicionValida = new Posicion(3, 3);

        assertDoesNotThrow(() -> mech1.moverseA(posicionValida, tablero));
        assertEquals(posicionValida, mech1.obtenerPosicion());
    }
}