package edu.fiuba.algo3.entrega_2;


import edu.fiuba.algo3.modelo.ataque.Direccion;
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

        Posicion posicionDeEmpuje =  new Posicion(1,2);
        Posicion posicionDeColision =  new Posicion(1,3);

        VekAvispon vekEmpuje = new VekAvispon();
        VekAvispon vekColision = new VekAvispon();

        int vidaInicialEmpuje = vekEmpuje.obtenerVida();
        int vidaInicialColision = vekColision.obtenerVida();

        tablero.colocar(vekEmpuje, posicionDeEmpuje);
        tablero.colocar(vekColision, posicionDeColision);

        tablero.empujar(vekEmpuje, Direccion.DERECHA);

        assertEquals(posicionDeEmpuje, vekEmpuje.obtenerPosicion());
        assertEquals(posicionDeColision, vekColision.obtenerPosicion());
        assertEquals(vekEmpuje, tablero.obtenerCasilla(posicionDeEmpuje).getUnidad());
        assertEquals(vekColision, tablero.obtenerCasilla(posicionDeColision).getUnidad());

        assertEquals(vidaInicialEmpuje - 1, vekEmpuje.obtenerVida());
        assertEquals(vidaInicialColision - 1, vekColision.obtenerVida());

    }

    @Test
    void AlEmpujarUnVekTerrestreAlAguaMuere() {
        Tablero tablero = new Tablero();

        Posicion posicionDeEmpuje =  new Posicion(0,2);

        VekSaltador vekEmpuje = new VekSaltador();

        tablero.colocar(vekEmpuje, posicionDeEmpuje);

        tablero.empujar(vekEmpuje, Direccion.IZQUIERDA);

        assertFalse(vekEmpuje.estaVivo());
        assertTrue(vekEmpuje.estaMuerta());

    }
    @Test
    void AlEmpujarUnVekAvisponAlAguaNoleAfecta() {
        Tablero tablero = new Tablero();

        Posicion posicionDeEmpuje =  new Posicion(0,2);
        Posicion posicionEmpujado =  new Posicion(0,1);

        VekAvispon vekEmpuje = new VekAvispon();

        tablero.colocar(vekEmpuje, posicionDeEmpuje);

        tablero.empujar(vekEmpuje, Direccion.IZQUIERDA);

        assertTrue(vekEmpuje.estaVivo());
        assertFalse(vekEmpuje.estaMuerta());

        assertNotEquals(0, vekEmpuje.obtenerVida());
        assertEquals(posicionEmpujado, vekEmpuje.obtenerPosicion());
    }

    @Test
    void unVekTerrestreSobreAcidoQuedaContaminadaYRecibeDobleDeDanio() {
        Tablero tablero = new Tablero();
        Posicion posicionSuelo =  new Posicion(2,6);
        Posicion posicionAcido =  new Posicion(3,6);
        VekSaltador vekTerrestre = new VekSaltador();
        int vidaInicial = vekTerrestre.obtenerVida();

        tablero.colocar(vekTerrestre, posicionSuelo);
        tablero.empujar(vekTerrestre, Direccion.ABAJO);

        tablero.obtenerCasilla(posicionAcido).recibirDanio(1, tablero, posicionAcido);

        assertEquals(vidaInicial - 2, vekTerrestre.obtenerVida());
    }

    @Test
    void unVekAvisponSobreAcidoNoSeContaminaYRecibeDanioNormal() {
        Tablero tablero = new Tablero();
        Posicion posicionSuelo =  new Posicion(2,6);
        Posicion posicionAcido =  new Posicion(3,6);
        VekAvispon vek = new VekAvispon();
        int vidaInicial = vek.obtenerVida();

        tablero.colocar(vek, posicionSuelo);
        tablero.empujar(vek, Direccion.ABAJO);

        tablero.obtenerCasilla(posicionAcido).recibirDanio(1, tablero, posicionAcido);

        assertEquals(vidaInicial - 1, vek.obtenerVida());

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
