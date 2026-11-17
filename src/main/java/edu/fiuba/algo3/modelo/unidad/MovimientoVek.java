package edu.fiuba.algo3.modelo.unidad;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.TipoCasilla;
import edu.fiuba.algo3.modelo.errors.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.unidad.errors.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.unidad.errors.PosicionFueraDelTableroException;

import java.util.*;

public abstract class MovimientoVek implements Movible {

    public static final int FILAS = 8;
    public static final int COLUMNAS = 8;

    protected final Set<TipoCasilla> transitables;
    protected final int rangoMovimiento;

    protected MovimientoVek(int rango, Set<TipoCasilla> transitables) {
        this.rangoMovimiento = rango;
        this.transitables = transitables;
    }

    protected abstract boolean puedeLlegar(Unidad unidad, Posicion destino, Tablero tablero);

    protected void validadDestino(Posicion posicionFinal, Tablero tablero) {
        if (!posicionFinal.estaEnElTablero(FILAS, COLUMNAS)) {
            throw new PosicionFueraDelTableroException();
        }

        Casilla casillaDestino = tablero.obtenerCasilla(posicionFinal);
        TipoCasilla tipoCasillaDestino = casillaDestino.getTipo();

        if (!transitables.contains(tipoCasillaDestino)) {
            throw new CasillaNoTransitableException();
        }

        if (casillaDestino.estaOcupada()) {
            throw new MovimientoEnPosicionOcupadaException();
        }
    }

    public void desplazar(Unidad unidad, Posicion posicionFinal, Tablero tablero) {
        Posicion posicionAnterior = unidad.getPosicion();
        Casilla casillaDestino = tablero.obtenerCasilla(posicionFinal);
        tablero.obtenerCasilla(posicionAnterior).liberar();
        casillaDestino.ocupar(unidad);
        unidad.actualizarPosicion(posicionFinal);

    }

    public boolean puedeMoverA(Unidad unidad, Posicion destino, Tablero tablero) {
        if (!destino.estaEnElTablero(FILAS, COLUMNAS)) return false;
        Casilla casilla = tablero.obtenerCasilla(destino);
        return transitables.contains(casilla.getTipo())
                && !casilla.estaOcupada()
                && puedeLlegar(unidad, destino, tablero);
    }

    public List<Posicion> posicionesAlcanzables(Unidad unidad, Tablero tablero) {
        List<Posicion> alcanzables = new ArrayList<>();
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                Posicion p = new Posicion(f, c);
                if (puedeMoverA(unidad, p, tablero)) alcanzables.add(p);
            }
        }
        return alcanzables;
    }
    protected boolean existeCaminoLibre(Unidad unidad, Posicion destino, Tablero tablero) {
        Set<Posicion> visitadas = new HashSet<>();
        Deque<Posicion> cola = new ArrayDeque<>();
        Map<Posicion, Integer> distancia = new HashMap<>();

        Posicion origen = unidad.getPosicion();
        cola.add(origen);
        visitadas.add(origen);
        distancia.put(origen, 0);

        while (!cola.isEmpty()) {
            Posicion actual = cola.poll();
            if (actual.equals(destino)) return true;
            int d = distancia.get(actual);
            if (d == this.rangoMovimiento) continue;

            for (Posicion vecina : tablero.obtenerPosicionesOrtogonales(actual)) {
                if (visitadas.contains(vecina)) continue;
                Casilla c = tablero.obtenerCasilla(vecina);
                if (!transitables.contains(c.getTipo()) || c.estaOcupada()) continue;
                visitadas.add(vecina);
                distancia.put(vecina, d + 1);
                cola.add(vecina);
            }
        }
        return false;
    }
}
