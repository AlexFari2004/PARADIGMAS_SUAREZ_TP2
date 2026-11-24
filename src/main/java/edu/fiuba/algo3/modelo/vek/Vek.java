package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.unidad.efecto.EfectoAtaque;
import edu.fiuba.algo3.modelo.unidad.mech.Mech;
import edu.fiuba.algo3.modelo.unidad.movilidad.Movilidad;
import edu.fiuba.algo3.modelo.mech.Terrestre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Vek extends Unidad {
    private Ataquevek ataqueAnunciado;
    private final int dañoDeAtaque;
    private final edu.fiuba.algo3.modelo.vek.MovimientoVek movimiento;
    private edu.fiuba.algo3.modelo.vek.EfectoAtaque efectoAtaque;

    protected Vek(int vida, int dañoDeAtaque, edu.fiuba.algo3.modelo.vek.EfectoAtaque efecto, edu.fiuba.algo3.modelo.vek.MovimientoVek movimiento) {
        this(vida, dañoDeAtaque, efecto, movimiento, new Terrestre());
    }

    protected Vek(int vida, int dañoDeAtaque, edu.fiuba.algo3.modelo.vek.EfectoAtaque efecto, edu.fiuba.algo3.modelo.vek.MovimientoVek movimiento, Movilidad movilidad) {
        super(vida, movimiento, movilidad);
        this.movimiento = movimiento;
        this.efectoAtaque = efecto;
        this.dañoDeAtaque = dañoDeAtaque;
    }

    protected abstract List<Posicion> posicionesAtacables(Posicion desde, Tablero tablero);

    public void planificarAccion(Tablero tablero) {
        ataqueAnunciado = null;

        List<Posicion> candidatas = new ArrayList<>();
        candidatas.add(getPosicion());
        candidatas.addAll(movimiento.posicionesAlcanzables(this, tablero));

        Set<Posicion> posicionesMechs = tablero.obtenerMechsVivos().stream()
                .map(Mech::getPosicion)
                .collect(Collectors.toSet());
        Set<Posicion> posicionesEdificios = new HashSet<>(tablero.obtenerPosicionEdificios());

        if (intentarAtacar(candidatas, tablero, posicionesMechs::contains)) return;
        if (intentarAtacar(candidatas, tablero, posicionesEdificios::contains)) return;
        acercarseAlEdificioMasCercano(candidatas, tablero);
    }


    public void resolverAtaque(Tablero tablero) {
        if (ataqueAnunciado == null) return;

        ataqueAnunciado.aplicar(tablero);
        ataqueAnunciado = null;
    }

    public void mover(Tablero tablero, Posicion destino) {
        if (destino.equals(getPosicion())) {
            return;
        }
        movimiento.mover(this, destino, tablero);
    }

    public void atacarA(Posicion objetivo) {
        ataqueAnunciado = new Ataquevek(this.getPosicion(), objetivo, dañoDeAtaque, efectoAtaque);
    }

    private boolean intentarAtacar(List<Posicion> candidatas, Tablero tablero,
                                   Predicate<Posicion> esObjetivo) {
        for (Posicion desde : candidatas) {
            for (Posicion objetivo : posicionesAtacables(desde, tablero)) {
                if (esObjetivo.test(objetivo)) {
                    mover(tablero, desde);
                    ataqueAnunciado = new Ataquevek(this.getPosicion(), objetivo, dañoDeAtaque, efectoAtaque);
                    return true;
                }
            }
        }
        return false;
    }

    private void acercarseAlEdificioMasCercano(List<Posicion> candidatas, Tablero tablero) {
        Posicion mejor = null;
        int mejorDistancia = Integer.MAX_VALUE;

        for (Posicion candidata : candidatas) {
            int d = distanciaAEdificioMasCercano(candidata, tablero);
            if (d < mejorDistancia) {
                mejorDistancia = d;
                mejor = candidata;
            }
        }
        if (mejor != null) mover(tablero, mejor);   // sin anunciar ataque
    }

    private int distanciaAEdificioMasCercano(Posicion origen, Tablero tablero) {
        int minima = Integer.MAX_VALUE;
        for (Posicion edificio : tablero.obtenerPosicionEdificios()) {
            minima = Math.min(minima, origen.distanciaA(edificio));
        }
        return minima;
    }

    @Override
    public void registrarEn(Tablero tablero) {
        tablero.registrarVek(this);
    }
}


