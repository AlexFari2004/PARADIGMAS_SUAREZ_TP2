package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import edu.fiuba.algo3.modelo.unidad.mech.Mech;
import edu.fiuba.algo3.modelo.unidad.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidad.vek.Vek;
import edu.fiuba.algo3.modelo.unidad.vek.VekAvispon;
import edu.fiuba.algo3.modelo.unidad.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.unidad.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class VekTest {
    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    void saltadorMuerdeCasillaAdyacenteConDosDeDaño() {
        Vek saltador = new VekSaltador();
        Mech mech = new MechCombate(new Puno());
        tablero.colocar(saltador, new Posicion(4, 3));
        tablero.colocar(mech, new Posicion(4, 4));
        int vidaInicial = mech.obtenerVida();

        saltador.planificarAccion(tablero);
        saltador.resolverAtaque(tablero);

        assertEquals(vidaInicial - 2, mech.obtenerVida());
    }

    @Test
    void avisponPicaCasillaAdyacenteConUnoDeDaño() {
        Vek avispon = new VekAvispon();
        Mech mech = new MechCombate(new Puno());
        tablero.colocar(avispon, new Posicion(4, 3));
        tablero.colocar(mech, new Posicion(4, 4));
        int vidaInicial = mech.obtenerVida();

        avispon.planificarAccion(tablero);
        avispon.resolverAtaque(tablero);

        assertEquals(vidaInicial - 1, mech.obtenerVida());
    }

    @Test
    void luciernagaAtacaEnLineaRectaConUnoDeDaño() {
        Vek luciernaga = new VekLuciernaga();
        Mech mech = new MechCombate(new Puno());
        tablero.colocar(luciernaga, new Posicion(3, 1));
        //falta implementar la logica de ataque linea recta, hardcodeado
        tablero.colocar(mech, new Posicion(3, 2));
        int vidaInicial = mech.obtenerVida();

        luciernaga.planificarAccion(tablero);
        luciernaga.resolverAtaque(tablero);

        assertEquals(vidaInicial - 1, mech.obtenerVida());
        assertEquals(new Posicion(3, 1), luciernaga.getPosicion());
    }
}
