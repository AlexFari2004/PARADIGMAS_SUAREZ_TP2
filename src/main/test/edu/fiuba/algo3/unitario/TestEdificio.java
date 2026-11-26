package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Edificio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestEdificio {

    @Test
    void edificioNoPuedeSerOcupadoYDetieneDisparos() {
        Edificio edificio = new Edificio();

        assertEquals(TipoCasilla.EDIFICIO, edificio.getTipo());
        assertFalse(edificio.puedeSerOcupada());
        assertTrue(edificio.detieneAtaque());
    }
}