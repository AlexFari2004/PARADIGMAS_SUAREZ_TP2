package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.entrega_1.MechPrueba;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AcidoTest {

    private static final Posicion SUELO = new Posicion(2, 3);
    private static final Posicion ACIDO = new Posicion(2, 2);

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void test01UnMechQueTerminoSobreAcidoRecibeElDobleDeDaño() {

        Mech mech = new MechPrueba(3);
        tablero.colocar(mech, SUELO);
        mech.mover(ACIDO, tablero);


        mech.recibirDaño(1);


        assertEquals(1, mech.obtenerVida());
    }

    @Test
    public void test02UnVekTerrestreQueTerminoSobreAcidoRecibeElDobleDeDaño() {

        VekSaltador saltador = new VekSaltador();
        tablero.colocar(saltador, SUELO);
        saltador.mover(ACIDO, tablero);


        saltador.recibirDaño(1);


        assertTrue(saltador.estaMuerta());
    }

    @Test
    public void test03UnaUnidadQueNoPisoAcidoRecibeElDañoNormal() {

        Mech mech = new MechPrueba(3);
        tablero.colocar(mech, SUELO);


        mech.recibirDaño(1);


        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test04UnMechQueSalioDelAcidoSigueRecibiendoElDobleDeDaño() {

        Mech mech = new MechPrueba(3);
        tablero.colocar(mech, SUELO);
        mech.mover(ACIDO, tablero);
        mech.reestablecerEstado();
        mech.mover(SUELO, tablero);


        mech.recibirDaño(1);


        assertEquals(1, mech.obtenerVida());
    }

    @Test
    public void test05ElAvisponQueTerminoSobreAcidoRecibeElDañoNormal() {

        VekAvispon avispon = new VekAvispon();
        tablero.colocar(avispon, SUELO);
        avispon.mover(ACIDO, tablero);


        avispon.recibirDaño(1);


        assertEquals(1, avispon.obtenerVida());
    }
}
