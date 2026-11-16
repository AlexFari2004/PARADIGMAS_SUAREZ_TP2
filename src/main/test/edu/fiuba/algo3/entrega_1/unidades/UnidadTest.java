package edu.fiuba.algo3.entrega_1.unidades;

import edu.fiuba.algo3.modelo.unidad.MovimientoMech;
import  edu.fiuba.algo3.modelo.unidad.Unidad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnidadTest {
    private static class UnidadATestear extends Unidad{

        public UnidadATestear(int vida){
            super(vida, new MovimientoMech());
        }
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
