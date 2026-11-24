package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class VekIA {
    public class VekIA {
        private final CriterioEmpate criterio;

        public VekIA(CriterioEmpate criterio) { this.criterio = criterio; }

        public void jugarTurno(Vek vek, Tablero tablero, AtaquesAnunciados anuncios) {
            List<Posicion> objetivos = tablero.posicionesAtacablesPorVek();   // edificios y Mechs vivos
            if (objetivos.isEmpty()) {
                return;
            }
            Posicion origen = vek.getPosicion();

            Posicion objetivo = elegirMasCercana(objetivos, origen);

            List<Posicion> candidatas = new ArrayList<>(vek.casillasAlcanzables(tablero));
            candidatas.add(origen);                          // quedarse quieto también es válido
            Posicion destino = elegirMasCercana(candidatas, objetivo);

            if (!destino.equals(origen)) {
                vek.moverA(destino, tablero);
            }
            anuncios.registrar(vek.anunciarAtaque(objetivo));
        }

        // Menor distancia a 'referencia'; si empatan, decide el criterio
        private Posicion elegirMasCercana(List<Posicion> candidatas, Posicion referencia) {
            Posicion mejor = candidatas.get(0);
            for (Posicion c : candidatas) {
                int distC = c.distanciaA(referencia);
                int distM = mejor.distanciaA(referencia);
                if (distC < distM || (distC == distM && criterio.comparar(c, mejor) < 0)) {
                    mejor = c;
                }
            }
            return mejor;
        }
    }
}
