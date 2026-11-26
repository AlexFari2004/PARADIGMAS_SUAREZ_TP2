package edu.fiuba.algo3.entrega_2;


import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VekTest {
    @Test
    void AlEmpujarUnVekContraOtroVekElVEkNoSeMueveYRecibeDanioDeColision() {
        Tablero tablero = new Tablero();

        Posicion posicionMech = new Posicion(1, 1);
        Posicion posicionDeEmpuje =  new Posicion(1,2);
        Posicion posicionDeColision =  new Posicion(1,3);

        MechCombate mech = new MechCombate(new Punio(1, new EfectoEmpuje()));
        VekLuciernaga vekEmpuje = new VekLuciernaga();
        VekAvispon vekColision = new VekAvispon();

        int vidaInicialEmpuje = vekEmpuje.obtenerVida();
        int vidaInicialColision = vekColision.obtenerVida();

        tablero.colocar(mech, posicionMech);
        tablero.colocar(vekEmpuje, posicionDeEmpuje);
        tablero.colocar(vekColision, posicionDeColision);


        mech.atacar(posicionDeEmpuje, tablero);
        assertEquals(posicionDeEmpuje, vekEmpuje.obtenerPosicion());
        assertEquals(posicionDeColision, vekColision.obtenerPosicion());
        assertEquals(vekEmpuje, tablero.obtenerCasilla(posicionDeEmpuje).getUnidad());
        assertEquals(vekColision, tablero.obtenerCasilla(posicionDeColision).getUnidad());

        assertEquals(vidaInicialEmpuje - 2, vekEmpuje.obtenerVida());
        assertEquals(vidaInicialColision - 1, vekColision.obtenerVida());

    }

    @Test
    void AlEmpujarUnVekTerrestreAlAguaMuere() {
        Tablero tablero = new Tablero();

        Posicion posicionDeEmpuje =  new Posicion(2,5);
        Posicion posicionMech = new Posicion(2, 6);
        MechCombate mech = new MechCombate(new Punio(0, new EfectoEmpuje()));
        VekSaltador vekEmpuje = new VekSaltador();

        tablero.colocar(vekEmpuje, posicionDeEmpuje);
        tablero.colocar(mech, posicionMech);
        mech.mover(posicionDeEmpuje, tablero);

        assertFalse(vekEmpuje.estaVivo());
        assertTrue(vekEmpuje.estaMuerta());

    }
    @Test
    void AlEmpujarUnVekAvisponAlAguaNoleAfecta() {
        Tablero tablero = new Tablero();

        Posicion posicionMech = new Posicion(2, 6);
        Posicion posicionDeEmpuje =  new Posicion(2,5);
        Posicion posicionEmpujado =  new Posicion(2,4);

        VekAvispon vekEmpuje = new VekAvispon();
        MechCombate mech = new MechCombate(new Punio(0, new EfectoEmpuje()));
        tablero.colocar(vekEmpuje, posicionDeEmpuje);
        tablero.colocar(mech, posicionMech);

        mech.atacar(posicionDeEmpuje, tablero);

        assertTrue(vekEmpuje.estaVivo());
        assertFalse(vekEmpuje.estaMuerta());

        assertNotEquals(0, vekEmpuje.obtenerVida());
        assertEquals(posicionEmpujado, vekEmpuje.obtenerPosicion());
    }

    @Test
    void unVekTerrestreSobreAcidoQuedaContaminadaYRecibeDobleDeDanio() {
        Tablero tablero = new Tablero();
        Posicion posicionMech = new Posicion(1, 6);
        Posicion posicionSuelo =  new Posicion(2,6);
        Posicion posicionAcido =  new Posicion(3,6);

        VekSaltador vekTerrestre = new VekSaltador();
        MechCombate mech = new MechCombate(new Punio(1, new EfectoEmpuje()));
        int vidaInicial = vekTerrestre.obtenerVida();

        tablero.colocar(vekTerrestre, posicionSuelo);
        tablero.colocar(mech, posicionMech);
        mech.atacar(posicionSuelo, tablero);

        tablero.obtenerCasilla(posicionAcido).recibirDanio(1, tablero, posicionAcido);

        assertEquals(vidaInicial - 2, vekTerrestre.obtenerVida());

    }

    @Test
    void unVekAvisponSobreAcidoNoSeContaminaYRecibeDanioNormal() {
        Tablero tablero = new Tablero();

        Posicion posicionMech = new Posicion(4, 6);
        Posicion posicionSuelo =  new Posicion(2,6);
        Posicion posicionAcido =  new Posicion(3,6);
        VekAvispon vek = new VekAvispon();
        MechCombate mech = new MechCombate(new Punio(2, new EfectoEmpuje()));
        int vidaInicial = vek.obtenerVida();

        tablero.colocar(vek, posicionAcido);
        tablero.colocar(mech, posicionMech);
        mech.atacar(posicionAcido, tablero);

        tablero.obtenerCasilla(posicionSuelo).recibirDanio(1, tablero, posicionAcido);

        assertTrue(vek.estaMuerta());

    }

    @Test
    void luciernagaContaminaAlImpactarYLaContaminacionNoSeAcumula() {
        Tablero tablero = new Tablero();
        Posicion posicionLuciernaga = new Posicion(1, 0);
        Posicion posicionObjetivo = new Posicion(1, 3);

        VekLuciernaga luciernaga = new VekLuciernaga();
        MechCombate objetivo = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicial = objetivo.obtenerVida();

        tablero.colocar(luciernaga, posicionLuciernaga);
        tablero.colocar(objetivo, posicionObjetivo);

        luciernaga.anunciarAtaque(Direccion.DERECHA).resolver(tablero);
        int vidaTrasPrimerImpacto = objetivo.obtenerVida();
        int danioEnPrimerImpacto = vidaInicial - vidaTrasPrimerImpacto;

        luciernaga.anunciarAtaque(Direccion.DERECHA).resolver(tablero);
        int vidaTrasSegundoImpacto = objetivo.obtenerVida();
        int danioSegundoImpacto = vidaTrasPrimerImpacto - vidaTrasSegundoImpacto;

        assertEquals(1, danioEnPrimerImpacto);
        assertEquals(2, danioSegundoImpacto);
    }

}
