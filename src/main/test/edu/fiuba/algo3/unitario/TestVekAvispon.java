package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.ataque.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestVekAvispon {
    @Test
    void avisponPicaCasillaAdyacenteConUnoDeDaño() {
        Tablero tablero = new Tablero();
        VekAvispon avispon = new VekAvispon();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicial = mech.obtenerVida();

        tablero.colocar(avispon, new Posicion(1, 2));
        tablero.colocar(mech, new Posicion(1, 3));

        avispon.anunciarAtaque(Direccion.DERECHA).resolver(tablero);

        assertTrue(mech.obtenerVida() < vidaInicial);
    }

    @Test
    void avisponNoDaniaCasillaVaciaSiElObjetivoSeMueveAntesDeResolverAtaque() {
        Tablero tablero = new Tablero();
        VekAvispon avispon = new VekAvispon();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicial = mech.obtenerVida();

        tablero.colocar(avispon, new Posicion(1, 2));
        tablero.colocar(mech, new Posicion(1, 3));

        AtaqueAnunciado ataque = avispon.anunciarAtaque(Direccion.DERECHA);
        mech.mover(new Posicion(1, 4), tablero);
        ataque.resolver(tablero);

        assertEquals(vidaInicial, mech.obtenerVida());
    }

    @Test
    void avisponPuedeTransitarSobreAguaYAcidoSinContaminarseNiSumergirse() {
        Tablero tablero = new Tablero();
        VekAvispon avispon = new VekAvispon();
        Posicion posicionSuelo = new Posicion(2, 1);
        Posicion posicionAcido = new Posicion(2, 2);
        Posicion posicionAgua = new Posicion(2, 4);

        tablero.colocar(avispon, posicionSuelo);
        List<Posicion> alcanzables = avispon.casillasAlcanzables(tablero);

        assertTrue(alcanzables.contains(posicionAcido));
        assertTrue(alcanzables.contains(posicionAgua));

        avispon.mover(tablero, posicionAcido);
        int vidaAntesDeAtaque = avispon.obtenerVida();
        tablero.obtenerCasilla(posicionAcido).recibirDanio(1, tablero, posicionAcido);

        assertEquals(vidaAntesDeAtaque - 1, avispon.obtenerVida());
    }
}
