package edu.fiuba.algo3.entrega_4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Logger;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpujeOrtogonal;
import edu.fiuba.algo3.modelo.errores.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.estado.EstadoContaminado;
import edu.fiuba.algo3.modelo.estado.EstadoNormal;
import edu.fiuba.algo3.modelo.estado.EstadoUnidad;
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
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import javafx.geometry.Pos;

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

        // TURNO 1: mover mech a casilla contaminada y deshacer

        // Fase veks (No hay veks, no ocurre nada)
        juego.avanzarFase(); 

        // Fase jugador
        Posicion destinoMovimiento1MechCanionTurno1 = new Posicion(2, 2);

        mechCanion.mover(destinoMovimiento1MechCanionTurno1, tablero);
        assertInstanceOf(EstadoContaminado.class, mechCanion.obtenerEstado());

        mechCombate.deshacerMovimiento(tablero);
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

        // Fase emergencia
        juego.avanzarFase();
        Casilla casillaConVekSaltador1 = tablero.obtenerCasilla(posicionEmergenciaVekSaltador1);
        assertInstanceOf(VekSaltador.class, casillaConVekSaltador1.getUnidad());

        // TURNO 2: lanzar vek saltador al agua

        // Fase veks
        juego.avanzarFase();

        // Fase jugador
        Posicion destinoAtaqueMechCanionTurno2 = new Posicion(1, 4);
        mechCanion.atacar(destinoAtaqueMechCanionTurno2, tablero);
        Posicion destinoEmpujeVek = new Posicion(2, 4);
        Casilla casillaAguaConVekMuerto = tablero.obtenerCasilla(destinoEmpujeVek);
        assertInstanceOf(VekSaltador.class, casillaAguaConVekMuerto.getUnidad());
        assertTrue(vekSaltador1.estaMuerta());

        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase();
        Casilla casillaConVekLuciernaga = tablero.obtenerCasilla(posicionEmergenciaVekLuciernaga);
        assertInstanceOf(VekLuciernaga.class, casillaConVekLuciernaga.getUnidad());

        // TURNO 3: VekLuciernaga escupe acido en mech y lo contamina
        // Fase veks
        juego.avanzarFase();

        // Fase jugador
        // mechCombate se interpone entre edificio (1, 5) y ataque vek desde (1, 7)
        Posicion destinoMovimientoMechCombateTurno3 = new Posicion(1, 6); 
        mechCombate.mover(destinoMovimientoMechCombateTurno3, tablero);
        
        Posicion destinoAtaqueMechCombateTurno3 = new Posicion(1, 7);
        mechCombate.atacar(destinoAtaqueMechCombateTurno3, tablero);
        assertEquals(vekLuciernaga.obtenerPosicion(), destinoAtaqueMechCombateTurno3); // No hubo empuje

        // Fase resolución
        int vidaMechCombateTurno3 = mechCombate.obtenerVida();
        juego.avanzarFase();
        assertInstanceOf(EstadoContaminado.class, mechCombate.obtenerEstado());
        assertEquals(mechCombate.obtenerVida(), vidaMechCombateTurno3 - 1);

        // Fase emergencia
        juego.avanzarFase();
        Casilla casillaConVekAvispon = tablero.obtenerCasilla(posicionEmergenciaVekAvispon);
        assertInstanceOf(VekAvispon.class, casillaConVekAvispon.getUnidad());

        // TURNO 4:
        // Fase veks
        juego.avanzarFase();

        // Fase jugador
        Posicion destinoAtaqueMechArtilleriaTurno4 = new Posicion(4, 3);
        mechArtilleria.atacar(destinoAtaqueMechArtilleriaTurno4, tablero);
        mechCombate.atacar(destinoAtaqueMechCombateTurno3, tablero); // Misma posicion ataque turno 3 (1,7)

        Posicion destinoMovimientoMechCanionTurno4 = new Posicion(1, 3);
        mechCanion.mover(destinoMovimientoMechCanionTurno4, tablero);


        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        int vidaMechCanionAntesEmergencia = mechCanion.obtenerVida();
        juego.avanzarFase(); //intenta emerger VekSaltador pero canion lo impide
        Casilla casillaConVekSaltador2 = tablero.obtenerCasilla(posicionEmergenciaVekSaltador2);
        assertInstanceOf(MechCombate.class, casillaConVekSaltador2.getUnidad());
        assertEquals(mechCanion.obtenerVida(), vidaMechCanionAntesEmergencia - 1);

        // TURNO 5:
        // Fase veks
        juego.avanzarFase();

        //Fase jugador
        Posicion destinoMovimientoMechArtilleriaTurno5 = new Posicion(5, 1);
        mechArtilleria.mover(destinoMovimientoMechArtilleriaTurno5, tablero);

        Posicion destinoAtaqueMechArtilleriaTurno5 = new Posicion(5, 3);
        mechArtilleria.atacar(destinoAtaqueMechArtilleriaTurno5, tablero);

        Posicion destinoAtaqueMechCanionTurno5 = new Posicion(1, 4);
        mechCanion.atacar(destinoAtaqueMechCanionTurno5, tablero);

        // Fase resolución
        juego.avanzarFase();

        // Fase emergencia
        juego.avanzarFase();
    }
}
