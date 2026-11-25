package edu.fiuba.algo3.modelo.vek;

import edu.fiuba.algo3.modelo.ataque.AtaqueAnunciado;
import edu.fiuba.algo3.modelo.ataque.AtaqueRegistrado;
import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class VekIA {

    private final CriterioEmpate criterio;

    public VekIA(CriterioEmpate criterio) {
        this.criterio = criterio;
    }

    public void jugarTurno(Vek vek, Tablero tablero, AtaqueRegistrado anuncios) {
        List<Posicion> candidatas = obtenerCandidatasOrdenadas(vek, tablero);

        Set<Posicion> posicionesEdificios = new HashSet<>(tablero.obtenerPosicionEdificios());
        Set<Posicion> posicionesMechs = tablero.obtenerMechsVivos().stream()
                .map(Mech::obtenerPosicion)
                .collect(Collectors.toSet());

        // Prioridad 1: moverse a una posición desde la que pueda atacar un Edificio
        if (intentarAtacarObjetivo(vek, candidatas, posicionesEdificios, tablero, anuncios)) {
            return;
        }

        // Prioridad 2: moverse a una posición desde la que pueda atacar un Mech
        if (intentarAtacarObjetivo(vek, candidatas, posicionesMechs, tablero, anuncios)) {
            return;
        }

        // Prioridad 3: acercarse al edificio más cercano, sin anunciar ataque
        acercarseAEdificioMasCercano(vek, candidatas, posicionesEdificios, tablero);
    }

    private List<Posicion> obtenerCandidatasOrdenadas(Vek vek, Tablero tablero) {
        List<Posicion> candidatas = new ArrayList<>();
        candidatas.add(vek.obtenerPosicion()); // Quedarse quieto también es válido
        candidatas.addAll(vek.casillasAlcanzables(tablero));
        candidatas.sort(criterio::comparar);
        return candidatas;
    }

    private boolean intentarAtacarObjetivo(Vek vek, List<Posicion> candidatas,
                                           Set<Posicion> objetivos, Tablero tablero,
                                           AtaqueRegistrado anuncios) {
        if (objetivos.isEmpty()) {
            return false;
        }

        for (Posicion candidata : candidatas) {
            for (Direccion direccion : Direccion.todas()) {
                Posicion impacto = vek.previsualizarImpactoDesde(candidata, direccion, tablero);
                if (tablero.estaDentro(impacto) && objetivos.contains(impacto)) {
                    vek.mover(tablero, candidata);
                    anuncios.registrar(vek.anunciarAtaque(direccion));
                    return true;
                }
            }
        }
        return false;
    }

    private void acercarseAEdificioMasCercano(Vek vek, List<Posicion> candidatas,
                                              Set<Posicion> edificios, Tablero tablero) {
        if (edificios.isEmpty()) {
            return;
        }

        Posicion mejorDestino = candidatas.get(0);
        int menorDistancia = distanciaMinimaAEdificios(mejorDestino, edificios);

        for (Posicion candidata : candidatas) {
            int distancia = distanciaMinimaAEdificios(candidata, edificios);
            if (distancia < menorDistancia
                    || (distancia == menorDistancia && criterio.comparar(candidata, mejorDestino) < 0)) {
                menorDistancia = distancia;
                mejorDestino = candidata;
            }
        }

        vek.mover(tablero, mejorDestino);
    }

    private int distanciaMinimaAEdificios(Posicion origen, Set<Posicion> edificios) {
        int minima = Integer.MAX_VALUE;
        for (Posicion edificio : edificios) {
            int d = origen.distanciaA(edificio);
            if (d < minima) {
                minima = d;
            }
        }
        return minima;
    }
}

