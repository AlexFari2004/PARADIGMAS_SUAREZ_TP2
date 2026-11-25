package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.mech.Canion;
import edu.fiuba.algo3.modelo.mech.MechCanion;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanionTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void test01CanionImpactaSoloALaPrimeraUnidadEnLineaRectaYNoALasDetras() {
        Canion canion = new Canion(1,new EfectoEmpuje());
        MechCanion mech = new MechCanion(canion);

        VekSaltador vekDelante = new VekSaltador();
        VekSaltador vekDetras = new VekSaltador();

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
        MechCanion mech = new MechCanion(new Canion(1,new EfectoEmpuje()));
        VekSaltador vekDetras = new VekSaltador();
        Posicion posMech = new Posicion(0, 2);
        Posicion posObstaculo = new Posicion(0, 3);
        Posicion posVekDetras = new Posicion(0, 4);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekDetras, posVekDetras);

        mech.atacar(new Posicion(0, 5), tablero);

        assertEquals(TipoCasilla.MONTANIA, tablero.obtenerCasilla(posObstaculo).getTipo());
        assertEquals(2, vekDetras.obtenerVida());
    }
}