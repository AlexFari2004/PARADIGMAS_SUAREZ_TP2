package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.CondicionVictoria;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CondicionVictoriaTest {
    private Tablero tablero;
    private RedElectrica red;
    private CondicionVictoria victoria;

    @BeforeEach
    void preparar() {
        tablero = new Tablero();
        red = new RedElectrica(5);
        tablero.colocar(new MechCombate(new Punio()), new Posicion(4, 0));
        victoria = new CondicionVictoria(tablero, 5,red);
    }

    @Test
    public void antesDeCompletarLosCincoTurnosNoHayVictoria() {

        assertFalse(victoria.verificarResultado());
    }

    @Test
    public void alCompletarLosCincoTurnosSinDerrotaHayVictoria() {
        //pasar 5 turno con controlador
        assertTrue(victoria.verificarResultado());
    }

    @Test
    public void completarLosCincoTurnosConLaRedEnCeroNoEsVictoria() {
        red.consumirEnergia(5);

        assertFalse(victoria.verificarResultado());
    }

    @Test
    public void completarLosCincoTurnosSinMechsVivosNoEsVictoria() {
        tablero.obtenerMechsVivos().forEach(m -> m.recibirDanio(100));

        assertFalse(victoria.verificarResultado());
    }

}
