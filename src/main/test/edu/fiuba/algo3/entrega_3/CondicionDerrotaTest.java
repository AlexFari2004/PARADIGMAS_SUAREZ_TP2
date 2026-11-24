package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.CondicionDerrota;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.mech.*;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CondicionDerrotaTest {
    private Tablero tablero;
    private RedElectrica red;
    private CondicionDerrota derrota;
    private List<Mech> mechs;

    @BeforeEach
    void preparar() {
        tablero = new Tablero();
        red = new RedElectrica(5);
        mechs = List.of(new MechCombate(new Punio()), new MechCanion(new Canion()), new MechDeArtilleria(new Artilleria()));
        for (int i = 0; i < mechs.size(); i++) {
            tablero.colocar(mechs.get(i), new Posicion(4, i));
        }
        derrota = new CondicionDerrota(tablero, red);
    }

    @Test
    public void sinNovedadesNoHayDerrota() {
        assertFalse(derrota.verificarResultado());
    }

    @Test
    public void conLaRedElectricaEnCeroHayDerrota() {
        red.consumirEnergia(5);

        assertTrue(derrota.verificarResultado());
    }

    @Test
    public void conLaRedConPuntosRestantesNoHayDerrota() {
        red.consumirEnergia(4);

        assertFalse(derrota.verificarResultado());
    }

    @Test
    public void conLosTresMechsDestruidosHayDerrota() {
        mechs.forEach(m -> m.recibirDanio(5));

        assertTrue(derrota.verificarResultado());
    }

}
