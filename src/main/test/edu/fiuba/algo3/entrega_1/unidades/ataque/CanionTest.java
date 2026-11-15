package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mock.TableroStub;
import edu.fiuba.algo3.modelo.unidad.mech.MechCanion;
import edu.fiuba.algo3.modelo.unidad.mech.arma.Canion;
import edu.fiuba.algo3.modelo.unidad.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CanionTest {

    private TableroStub tablero;

    @BeforeEach
    void setUp() {
        tablero = new TableroStub();
    }

    @Test
    public void test01CanionImpactaSoloALaPrimeraUnidadEnLineaRectaYNoALasDetras() {
        Canion canion = new Canion();
        MechCanion mech = new MechCanion(canion);

        Saltador vekDelante = new Saltador();
        Saltador vekDetras = new Saltador();

        Posicion posMech = new Posicion(3, 1);
        Posicion posVekDelante = new Posicion(3, 3);
        Posicion posVekDetras = new Posicion(3, 5);

        tablero.obtenerCasilla(posMech).colocarUnidad(mech);
        mech.setPosicion(posMech);

        tablero.obtenerCasilla(posVekDelante).colocarUnidad(vekDelante);
        vekDelante.setPosicion(posVekDelante);

        tablero.obtenerCasilla(posVekDetras).colocarUnidad(vekDetras);
        vekDetras.setPosicion(posVekDetras);

        // Act: Disparamos en dirección al primer vek
        mech.atacar(posVekDelante, tablero);

        // Assert
        assertTrue(vekDelante.obtenerVida() < 2);
        assertEquals(2, vekDetras.obtenerVida());
    }
}