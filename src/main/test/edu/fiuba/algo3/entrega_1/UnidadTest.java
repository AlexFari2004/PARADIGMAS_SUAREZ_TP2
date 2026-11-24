package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.vek.MovimientoMech;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnidadTest {
    private static class UnidadATestear extends Unidad {

        public UnidadATestear(int vida) {
            super(vida, new MovimientoMech());
        }

        public void registrarEn(Tablero tablero) {
        }

        ;
    }

    @Test
    public void unaUnidadComienzaViva() {

        Unidad unidad = new UnidadATestear(3);

        assertFalse(unidad.estaMuerta());
    }

    @Test
    public void unaUnidadSinContaminarRecibeDañoNormal() {

        Unidad unidad = new UnidadATestear(3);

        unidad.recibirDaño(2);

        assertFalse(unidad.estaMuerta());

        unidad.recibirDaño(1);

        assertTrue(unidad.estaMuerta());
    }

    @Test
    public void unaUnidadContaminadaRecibeElDobleDeDaño() {

        Unidad unidad = new UnidadATestear(3);

        unidad.contaminar();
        unidad.recibirDaño(2);

        assertTrue(unidad.estaMuerta());
    }

}
