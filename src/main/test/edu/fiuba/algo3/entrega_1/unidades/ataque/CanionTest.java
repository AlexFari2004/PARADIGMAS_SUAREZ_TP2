package edu.fiuba.algo3.entrega_1.unidades.ataque;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.unidad.mech.MechCanion;
import edu.fiuba.algo3.modelo.unidad.arma.Canion;
import edu.fiuba.algo3.modelo.unidad.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CanionTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
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
        Posicion destino = new Posicion(3, 6);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekDelante, posVekDelante);
        tablero.colocar(vekDetras, posVekDetras);

        mech.atacar(destino, tablero);

        assertEquals(1, vekDelante.obtenerVida());
        assertEquals(2, vekDetras.obtenerVida());
    }

    @Test
    public void test02CanionSeDetieneEnObstaculo() {
        MechCanion mech = new MechCanion(new Canion());
        Saltador vekDetras = new Saltador();
        Posicion posMech = new Posicion(0, 1);
        Posicion posObstaculo = new Posicion(0, 3);
        Posicion posVekDetras = new Posicion(0, 4);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekDetras, posVekDetras);

        mech.atacar(new Posicion(0, 5), tablero);

        assertEquals(TipoCasilla.MONTAÑA, tablero.obtenerCasilla(posObstaculo).getTipo());
        assertEquals(2, vekDetras.obtenerVida());
    }
}