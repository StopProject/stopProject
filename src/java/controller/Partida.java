package controller;

import java.util.ArrayList;
import java.util.List;

public final class Partida {

    private boolean emCurso = true;
    private List<Rodada> rodadas;
    private static Partida partida;
    private List<Jogador> jogadores;

    public static Partida getIntance() {

        if (partida==null) {

            partida = new Partida();
        }
        return partida;
    }

    public void adicionarJogador(Jogador jogador) {

        getJogadores().add(jogador);

    }

    private Partida() {
        rodadas = new ArrayList();
        jogadores = new ArrayList();
    }

    public void adicionarRodada(Rodada rodada) {
        rodadas.add(rodada);
    }

    /**
     * @return the jogadores
     */
    public List<Jogador> getJogadores() {
        return jogadores;
    }

    // public boolean validaJogador(String nome) {
    // for(){
    //}
    // }
}
