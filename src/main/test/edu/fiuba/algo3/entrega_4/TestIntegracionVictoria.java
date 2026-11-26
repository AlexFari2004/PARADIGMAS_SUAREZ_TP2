package edu.fiuba.algo3.entrega_4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpujeOrtogonal;
import edu.fiuba.algo3.modelo.errores.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.errores.AtaqueDesdeAguaException;
import edu.fiuba.algo3.modelo.estado.EstadoContaminado;
import edu.fiuba.algo3.modelo.estado.EstadoNormal;
import edu.fiuba.algo3.modelo.mech.Artilleria;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.mech.MechCanion;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.MechDeArtilleria;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.mech.Canion;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.vek.VekSaltador;

import org.junit.jupiter.api.Test;


public class TestIntegracionVictoria {
    private void narrar(String mensaje) {
        Logger.log(mensaje);
    }
    private void banner(String titulo) {
        Logger.log("");
        Logger.log("################################################################");
        Logger.log("###  " + titulo);
        Logger.log("################################################################");
        Logger.log("");
    }

    @BeforeEach
    public void habilitarLog() {
        Logger.setEnabled(true);
    }

    @Test
    public void ElJugadorGanaElJuegoResistiendoLos5Turnos() {
        banner("Juego ganado por el jugador");
        narrar("Inciando el juego");
        Mech mechCombate = new MechCombate(new Punio(2,new EfectoEmpuje()));
        Mech mechArtilleria = new MechDeArtilleria(new Artilleria(1,new EfectoEmpujeOrtogonal()));
        Mech mechCanion = new MechCanion(new Canion(1, new EfectoEmpuje()));

        List<Mech> mechs = List.of(mechCanion, mechArtilleria, mechCombate);
        List<Posicion> posicionesIniciales = List.of(
                new Posicion(1, 2),
                new Posicion(4, 1),
                new Posicion(2, 6)
        );

        Posicion posicionEmergenciaVekSaltador1 = new Posicion(1, 4);
        Posicion posicionEmergenciaVekLuciernaga = new Posicion(3, 7);
        Posicion posicionEmergenciaVekAvispon = new Posicion(4, 4);
        Posicion posicionEmergenciaVekSaltador2 = new Posicion(1, 3);

        Vek vekSaltador1 = new VekSaltador();
        Vek vekLuciernaga =  new VekLuciernaga();
        Vek vekAvispon = new VekAvispon();
        Vek vekSaltador2 = new VekSaltador();

        List<Emergencia> emergencias = List.of(
                new Emergencia(vekSaltador1, posicionEmergenciaVekSaltador1),
                new Emergencia(vekLuciernaga, posicionEmergenciaVekLuciernaga),
                new Emergencia(vekAvispon, posicionEmergenciaVekAvispon),
                new Emergencia(vekSaltador2, posicionEmergenciaVekSaltador2)
        );

        Juego juego = new Juego(mechs, posicionesIniciales, emergencias);
        Tablero tablero = juego.obtenerTablero();
        int energiaInicialRedElectrica = tablero.obtenerEnergiaRedElectrica();

        // TURNO 1: mover mech a casilla contaminada y deshacer
        // Fase vek
        // No hay Veks en tablero, no deberia pasar nada
        juego.avanzarFase(); 
        assertTrue(tablero.obtenerVeks().isEmpty());

        // Fase jugador
        Posicion destinoMovimiento1MechCanionTurno1 = new Posicion(2, 2);

        mechCanion.mover(destinoMovimiento1MechCanionTurno1, tablero);
        assertInstanceOf(EstadoContaminado.class, mechCanion.obtenerEstado());

        mechCanion.deshacerMovimiento(tablero);;
        assertInstanceOf(EstadoNormal.class, mechCanion.obtenerEstado());

        Posicion destinoMovimiento2MechCanionTurno1 = new Posicion(0, 4);
        mechCanion.mover(destinoMovimiento2MechCanionTurno1, tablero);
        assertThrows(
            AccionNoPermitidaException.class,
            () -> mechCanion.mover(new Posicion(0, 5), tablero)
        );

        juego.avanzarFase();

        // Fase resolución
        juego.avanzarFase();
        assertEquals(tablero.obtenerEnergiaRedElectrica(), energiaInicialRedElectrica);

        // Fase emergencia
        juego.avanzarFase();
        Casilla casillaConVekSaltador1 = tablero.obtenerCasilla(posicionEmergenciaVekSaltador1);
        assertInstanceOf(VekSaltador.class, casillaConVekSaltador1.getUnidad());

        // TURNO 2: lanzar vek saltador al agua
        // Fase vek
        // El Saltador este al lado del edificio (1, 5), asi que deberia quedarse en (1, 4) para atacarlo.
        juego.avanzarFase();
        assertEquals(new Posicion(1, 4), vekSaltador1.obtenerPosicion());

        // Fase jugador
        Posicion destinoAtaqueMechCanionTurno2 = new Posicion(1, 4);
        mechCanion.atacar(destinoAtaqueMechCanionTurno2, tablero);
        Posicion destinoEmpujeVek = new Posicion(2, 4);
        Casilla casillaAguaConVekMuerto = tablero.obtenerCasilla(destinoEmpujeVek);
        assertFalse(casillaAguaConVekMuerto.estaOcupada());
        assertTrue(vekSaltador1.estaMuerta());
        juego.avanzarFase();

        // Fase resolución
        juego.avanzarFase();
        assertEquals(tablero.obtenerEnergiaRedElectrica(), energiaInicialRedElectrica);

        // Fase emergencia
        juego.avanzarFase();
        Casilla casillaConVekLuciernaga = tablero.obtenerCasilla(posicionEmergenciaVekLuciernaga);
        assertInstanceOf(VekLuciernaga.class, casillaConVekLuciernaga.getUnidad());

        // TURNO 3: VekLuciernaga escupe acido en mech y lo contamina
        // Fase vek
        // La luciernaga puede acercarse a edificio en (1, 5) yendo a (1, 7), deberia acercarse
        juego.avanzarFase();
        assertEquals(new Posicion(1, 7), vekLuciernaga.obtenerPosicion());

        // Fase jugador
        // mechCombate se interpone en (1, 6) entre el Vek y el edificio, el ataque debería impactarlo.
        Posicion destinoMovimientoMechCombateTurno3 = new Posicion(1, 6); 
        mechCombate.mover(destinoMovimientoMechCombateTurno3, tablero);
        
        int vidaVekLuciernagaTurno4 = vekLuciernaga.obtenerVida();
        Posicion destinoAtaqueMechCombateTurno3 = new Posicion(1, 7);
        mechCombate.atacar(destinoAtaqueMechCombateTurno3, tablero);
        assertEquals(vekLuciernaga.obtenerVida(), vidaVekLuciernagaTurno4 - 2);
        assertEquals(vekLuciernaga.obtenerPosicion(), destinoAtaqueMechCombateTurno3); // No hubo empuje
        juego.avanzarFase();

        // Fase resolución
        int vidaMechCombateTurno3 = mechCombate.obtenerVida();
        juego.avanzarFase();
        assertInstanceOf(EstadoContaminado.class, mechCombate.obtenerEstado());
        assertEquals(mechCombate.obtenerVida(), vidaMechCombateTurno3 - 1);
        assertEquals(tablero.obtenerEnergiaRedElectrica(), energiaInicialRedElectrica);

        // Fase emergencia
        juego.avanzarFase();
        Casilla casillaConVekAvispon = tablero.obtenerCasilla(posicionEmergenciaVekAvispon);
        assertInstanceOf(VekAvispon.class, casillaConVekAvispon.getUnidad());

        // TURNO 4:
        // El vek avispon puede atacar el edificio (1, 5) desde (1, 4), así que deberia ir moverse ahi.
        juego.avanzarFase();
        assertEquals(new Posicion(1, 4), vekAvispon.obtenerPosicion());

        // Fase jugador
        Posicion destinoAtaqueMechArtilleriaTurno4 = new Posicion(4, 3);
        mechArtilleria.atacar(destinoAtaqueMechArtilleriaTurno4, tablero);
        Posicion posicionVekLuciernagaTurno4 = vekLuciernaga.obtenerPosicion();
        mechCombate.atacar(posicionVekLuciernagaTurno4, tablero);
        assertTrue(vekLuciernaga.estaMuerta());

        Posicion destinoMovimientoMechCanionTurno4 = new Posicion(1, 3);
        mechCanion.mover(destinoMovimientoMechCanionTurno4, tablero);
        juego.avanzarFase();

        // Fase resolución
        juego.avanzarFase();
        assertEquals(tablero.obtenerEnergiaRedElectrica(), energiaInicialRedElectrica - 1);
        assertEquals(TipoCasilla.SUELO, tablero.obtenerCasilla(new Posicion(1, 5)).getTipo()); // Edificio destruido

        // Fase emergencia
        int vidaMechCanionAntesEmergencia = mechCanion.obtenerVida();
        juego.avanzarFase(); //intenta emerger VekSaltador pero canion lo impide
        Casilla casillaConVekSaltador2 = tablero.obtenerCasilla(posicionEmergenciaVekSaltador2);
        assertInstanceOf(MechCanion.class, casillaConVekSaltador2.getUnidad());
        assertEquals(mechCanion.obtenerVida(), vidaMechCanionAntesEmergencia - 1);

        // TURNO 5:
        // Fase vek
        // El edificio en (1, 5) ya fue destruido, el vek avispon se mueve a (4, 3) para atacar (5, 3).
        juego.avanzarFase();
        assertEquals(new Posicion(4, 3), vekAvispon.obtenerPosicion());

        // Fase jugador
        Posicion destinoMovimientoMechArtilleriaTurno5 = new Posicion(5, 1);
        mechArtilleria.mover(destinoMovimientoMechArtilleriaTurno5, tablero);

        Posicion destinoAtaqueMechArtilleriaTurno5 = new Posicion(5, 3);
        assertThrows(
            AtaqueDesdeAguaException.class,
            () -> mechArtilleria.atacar(destinoAtaqueMechArtilleriaTurno5, tablero)
        );

        Posicion destinoAtaqueMechCanionTurno5 = vekAvispon.obtenerPosicion();
        // mechCanion empuja al vek avispon contra (5, 3), destruye el edificio y lo mata antes de resolver su ataque.
        mechCanion.atacar(destinoAtaqueMechCanionTurno5, tablero);

        assertTrue(vekAvispon.estaMuerta());
        assertEquals(TipoCasilla.SUELO, tablero.obtenerCasilla(new Posicion(5, 3)).getTipo());
        juego.avanzarFase();

        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase();
        assertTrue(juego.gano());
        assertEquals(tablero.obtenerEnergiaRedElectrica(), energiaInicialRedElectrica - 2);
    }
}
