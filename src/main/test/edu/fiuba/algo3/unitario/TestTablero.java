package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.errores.UnidadYaColocadaException;
import edu.fiuba.algo3.modelo.mech.Artilleria;
import edu.fiuba.algo3.modelo.mech.MechDeArtilleria;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.fiuba.algo3.modelo.tablero.TipoCasilla.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestTablero {
    private Tablero tablero;
    private RedElectrica redElectrica;

    private static final TipoCasilla[][] tableroPropio = {
            {AGUA, AGUA, SUELO, MONTANIA, SUELO, SUELO, SUELO, EDIFICIO},
            {AGUA, SUELO, SUELO, SUELO, SUELO, EDIFICIO, SUELO, EDIFICIO},
            {SUELO, SUELO, ACIDO, SUELO, AGUA, AGUA, SUELO, SUELO},
            {AGUA, SUELO, SUELO, SUELO, SUELO, SUELO, ACIDO, SUELO},
            {SUELO, SUELO, MONTANIA, SUELO, SUELO, SUELO, SUELO, MONTANIA},
            {SUELO, AGUA, SUELO, EDIFICIO, SUELO, SUELO, SUELO, SUELO},
            {SUELO, SUELO, AGUA, SUELO, SUELO, AGUA, SUELO, SUELO},
            {ACIDO, SUELO, SUELO, ACIDO, SUELO, SUELO, SUELO, EDIFICIO}

    };

    @BeforeEach
    void setUp() {
        redElectrica = new RedElectrica();
        tablero = new Tablero(tableroPropio, redElectrica);
    }

    @Test
    void obtenerCasillaDentroDelTableroDevuelveElTipoCasillaCorrectamente() {
        Casilla casilla = tablero.obtenerCasilla(new Posicion(0, 0));

        assertEquals(TipoCasilla.AGUA, casilla.getTipo());
    }
    @Test
    void estaDentroDebeValidaCorrectamenteLasCasillasDentroDelTablero() {
        assertTrue(tablero.estaDentro(new Posicion(0, 0)));
        assertTrue(tablero.estaDentro(new Posicion(7, 7)));
        assertFalse(tablero.estaDentro(new Posicion(-1, 0)));
        assertFalse(tablero.estaDentro(new Posicion(0, 8)));
    }

    @Test
    void obtenerCasillasFueraDeLimiteDebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> tablero.obtenerCasilla(new Posicion(-1, 0)));
        assertThrows(IllegalArgumentException.class, () -> tablero.obtenerCasilla(new Posicion(8, 0)));
    }

    @Test
    void obtenerCasillasOrtogonalesEnUnposicionDevuelveCuatroCasillas() {
        Posicion posicion = new Posicion(3, 3);
        List<Casilla> ortogonales = tablero.obtenerCasillasOrtogonales(posicion);

        assertEquals(4, ortogonales.size());
    }

    @Test
    void obtenerPosicionesOrtogonalesEnUnPosicionDevuelveCuatroPosiciones() {
        Posicion posicion = new Posicion(2, 2);
        List<Posicion> ortogonales = tablero.obtenerPosicionesOrtogonales(posicion);

        assertEquals(4, ortogonales.size());
    }

    @Test
    void recibirCasillasDebeInicializaElTableroCorrectamente() {
        Posicion posicion = new Posicion(1, 1);
        List<Casilla> casillas = tablero.obtenerCasillasOrtogonales(posicion);
        Casilla casilla1 = casillas.get(0);
        Casilla casilla2 = casillas.get(1);
        Casilla casilla3 = casillas.get(2);
        Casilla casilla4 = casillas.get(3);

        assertEquals(TipoCasilla.AGUA, casilla1.getTipo());
        assertEquals(TipoCasilla.SUELO, casilla2.getTipo());
        assertEquals(TipoCasilla.AGUA, casilla3.getTipo());
        assertEquals(TipoCasilla.SUELO, casilla4.getTipo());

    }

    @Test
    void reemplazarCasillaFueraDeLimiteDebeLanzarExcepcion() {
        Casilla casilla = TipoCasilla.EDIFICIO.crearCasilla();

        assertThrows(IllegalArgumentException.class, () -> tablero.reemplazarCasilla(new Posicion(8, 8), casilla));
    }
    @Test
    void consumirTodaLaEnergiaLaRedElectricaDebeAgotarse() {
        int energiaTotal = tablero.obtenerEnergiaRedElectrica();

        tablero.consumirRedElectrica(energiaTotal);

        assertTrue(tablero.redElectricaAgotada());
    }

    @Test
    void destruirEdificioReemplazaCasillaPorSueloYConsumeUnPuntoDeRedElectrica() {
        Posicion posicionEdificio = new Posicion(0, 7);
        int energiaInicial = tablero.obtenerEnergiaRedElectrica();

        tablero.destruirEdificioEn(posicionEdificio);

        assertEquals(TipoCasilla.SUELO, tablero.obtenerCasilla(posicionEdificio).getTipo());
        assertEquals(energiaInicial - 1, tablero.obtenerEnergiaRedElectrica());
        assertEquals(4, tablero.obtenerPosicionEdificios().size());
    }

    @Test
    void obtenerPosicionesDeEdificiosDevuelveLasPosiciones() {
        List<Posicion> edificios = tablero.obtenerPosicionEdificios();

        assertEquals(5, edificios.size());
        assertTrue(edificios.contains(new Posicion(0, 7)));
        assertTrue(edificios.contains(new Posicion(1, 7)));
        assertTrue(edificios.contains(new Posicion(1, 5)));
        assertTrue(edificios.contains(new Posicion(5, 3)));
        assertTrue(edificios.contains(new Posicion(7, 7)));
    }

    @Test
    void colocarUnidadEnCasillaValidaDebeActualizarLaPosicion() {
        MechDeArtilleria mech = new MechDeArtilleria(new Artilleria(1,new SinEfecto()));

        Posicion posicion = new Posicion(0, 0);
        tablero.colocar(mech, posicion);

        assertEquals(mech, tablero.obtenerCasilla(posicion).getUnidad());
    }

    @Test
    void colocarUnidadYaPosicionadoDebeLanzarExcepcion() {
        Posicion posicion = new Posicion(1, 1);
        Posicion otraPosicion = new Posicion(1, 1);

        MechDeArtilleria mech = new MechDeArtilleria(new Artilleria(1,new SinEfecto()));

        tablero.colocar(mech, posicion);
        assertThrows(UnidadYaColocadaException.class, () -> tablero.colocar(mech, otraPosicion));
    }

    @Test
    void obtenerMechsVivosDebeDevolverLasUnidadesCorrespondientes() {
        MechDeArtilleria mech1 = new MechDeArtilleria(new Artilleria(1,new SinEfecto()));
        MechDeArtilleria mech2 = new MechDeArtilleria(new Artilleria(1,new SinEfecto()));
        MechDeArtilleria mech3 = new MechDeArtilleria(new Artilleria(1,new SinEfecto()));

        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(1, 0);
        Posicion posicion3 = new Posicion(2, 0);

        tablero.colocar(mech1, posicion1);
        tablero.colocar(mech2, posicion2);
        tablero.colocar(mech3, posicion3);

        assertEquals(3, tablero.obtenerMechsVivos().size());
    }

    @Test
    void obtenerVeksDebeDevolverLasUnidadesCorrespondientes() {
        VekAvispon vek1 = new VekAvispon();
        VekAvispon vek2 = new VekAvispon();
        VekAvispon vek3 = new VekAvispon();

        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(1, 0);
        Posicion posicion3 = new Posicion(2, 0);

        tablero.colocar(vek1, posicion1);
        tablero.colocar(vek2, posicion2);
        tablero.colocar(vek3, posicion3);

        assertEquals(3, tablero.obtenerVeks().size());
    }

    @Test
    void empujarUnaUnidadDebeDesplazarHaciaDireccionCorrespondiente() {
        VekAvispon vek1 = new VekAvispon();

        Posicion origen = new Posicion(1, 1);
        Posicion destino = new Posicion(1, 2);

        tablero.colocar(vek1, origen);
        tablero.empujar(vek1, Direccion.DERECHA);

        assertTrue(tablero.obtenerCasilla(destino).estaOcupada()
                || vek1.obtenerPosicion() != origen
        );
    }

}
