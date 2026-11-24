package edu.fiuba.algo3.modelo.movimiento;

import edu.fiuba.algo3.modelo.errores.CasillaNoTransitableException;
import edu.fiuba.algo3.modelo.errores.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.errores.MovimientoFueraDeRangoException;
import edu.fiuba.algo3.modelo.errores.PosicionFueraDelTableroException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.Unidad;

import java.util.*;

public abstract class MovimientoVek implements Movible {

    public static final int FILAS = 8;
    public static final int COLUMNAS = 8;

    protected final Set<TipoCasilla> transitables;
    protected final Set<TipoCasilla> destinosValidos;
    protected final int rangoMovimiento;

    protected MovimientoVek(int rango, Set<TipoCasilla> transitables, Set<TipoCasilla> destinosValidos) {
        this.rangoMovimiento = rango;
        this.transitables = transitables;
        this.destinosValidos = destinosValidos;
    }

    @Override
    public final void mover(Unidad unidad, Posicion destino, Tablero tablero) {
        validarDestino(destino, tablero);
        if (!puedeLlegar(unidad, destino, tablero)) {
            throw new MovimientoFueraDeRangoException("Movimiento inalcanzable: fuera del rango.");
        }
        desplazar(unidad, destino, tablero);
    }

    protected abstract boolean puedeLlegar(Unidad unidad, Posicion destino, Tablero tablero);

    protected void validarDestino(Posicion posicionFinal, Tablero tablero) {
        if (!posicionFinal.estaEnElTablero(FILAS, COLUMNAS)) {
            throw new PosicionFueraDelTableroException();
        }

        Casilla casillaDestino = tablero.obtenerCasilla(posicionFinal);
        if (!destinosValidos.contains(casillaDestino.getTipo())) {
            throw new CasillaNoTransitableException();
        }

        if (casillaDestino.estaOcupada()) {
            throw new MovimientoEnPosicionOcupadaException();
        }
    }

    public void desplazar(Unidad unidad, Posicion posicionFinal, Tablero tablero) {
        Posicion posicionAnterior = unidad.obtenerPosicion();
        Casilla casillaDestino = tablero.obtenerCasilla(posicionFinal);

        tablero.obtenerCasilla(posicionAnterior).liberar();
        casillaDestino.ocupar(unidad);
        unidad.actualizarPosicion(posicionFinal);

    }

    public boolean puedeMoverA(Unidad unidad, Posicion destino, Tablero tablero) {
        if (!destino.estaEnElTablero(FILAS, COLUMNAS)) return false;

        Casilla casilla = tablero.obtenerCasilla(destino);

        return destinosValidos.contains(casilla.getTipo())
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

        Posicion origen = unidad.obtenerPosicion();
        cola.add(origen);
        visitadas.add(origen);
        distancia.put(origen, 0);

        while (!cola.isEmpty()) {
            Posicion actual = cola.poll();
            if (actual.equals(destino)) return true;
            int dist = distancia.get(actual);
            if (dist == this.rangoMovimiento) continue;

            for (Posicion ortogonal : tablero.obtenerPosicionesOrtogonales(actual)) {
                if (visitadas.contains(ortogonal)) continue;

                Casilla casilla = tablero.obtenerCasilla(ortogonal);

                if (!transitables.contains(casilla.getTipo()) || casilla.estaOcupada()) continue;
                visitadas.add(ortogonal);
                distancia.put(ortogonal, dist + 1);
                cola.add(ortogonal);
            }
        }
        return false;
    }
}
