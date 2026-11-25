package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.entrega_1.MechPrueba;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.errores.AtaqueDesdeAguaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {

    private static final Posicion AGUA = new Posicion(2, 4);
    private static final Posicion AL_LADO_DEL_AGUA = new Posicion(2, 3);
    private static final Posicion DESDE_DONDE_SE_GOLPEA = new Posicion(2, 2);
    private static final Posicion SUELO = new Posicion(3, 3);
    private static final Posicion DEBAJO_DEL_SUELO = new Posicion(4, 3);
    private static final Posicion OBJETIVO = new Posicion(3, 4);

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void test01UnVekTerrestreEmpujadoAlAguaMuere() {

        VekLuciernaga luciernaga = new VekLuciernaga();
        tablero.colocar(luciernaga, AL_LADO_DEL_AGUA);
        Punio punio = new Punio(2, new EfectoEmpuje());


        punio.atacar(DESDE_DONDE_SE_GOLPEA, AL_LADO_DEL_AGUA, tablero);


        assertEquals(AGUA, luciernaga.obtenerPosicion());
        assertTrue(luciernaga.estaMuerta());
    }

    @Test
    public void test02UnVekTerrestreEmpujadoSinCaerAlAguaSobrevive() {

        VekLuciernaga luciernaga = new VekLuciernaga();
        tablero.colocar(luciernaga, SUELO);
        Punio punio = new Punio(2,new EfectoEmpuje());


        punio.atacar(DEBAJO_DEL_SUELO, SUELO, tablero);


        assertFalse(luciernaga.estaMuerta());
    }

    @Test
    public void test03ElAvisponQueTerminaEnElAguaNoMuere() {

        VekAvispon avispon = new VekAvispon();
        tablero.colocar(avispon, SUELO);


        avispon.mover(AGUA, tablero);


        assertEquals(AGUA, avispon.obtenerPosicion());
        assertFalse(avispon.estaMuerta());
    }

    @Test
    public void test04UnMechQueTerminaEnElAguaNoMuere() {

        Mech mech = new MechPrueba(3);
        tablero.colocar(mech, SUELO);


        mech.mover(AGUA, tablero);


        assertFalse(mech.estaMuerta());
    }

    @Test
    public void test05UnMechEnElAguaNoPuedeAtacar() {

        Mech mech = new MechPrueba(3);
        tablero.colocar(mech, SUELO);
        mech.mover(AGUA, tablero);


        Executable atacar = () -> mech.atacar(OBJETIVO, tablero);


        assertThrows(AtaqueDesdeAguaException.class, atacar);
    }

    @Test
    public void test06UnMechFueraDelAguaPuedeAtacar() {

        Mech mech = new MechPrueba(3);
        tablero.colocar(mech, SUELO);


        Executable atacar = () -> mech.atacar(OBJETIVO, tablero);


        assertDoesNotThrow(atacar);
    }
}
