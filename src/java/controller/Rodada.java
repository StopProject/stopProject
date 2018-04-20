package controller;

import java.util.ArrayList;
import java.util.List;

public final class Rodada {

    private char letra;
    private final List<Jogador> jogadors = new ArrayList();

    public Rodada(char letra) {
        this.letra = letra;

    }

    public void adicionarJogador(Jogador jogador) {
        jogadors.add(jogador);
    }

    public void listarJogadores() {

        this.jogadors.stream().forEach((s) -> {
            System.out.println(s.getNome());
        });
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public char getLetra() {
        return letra;
    }

    public void isJogando() {
        //partida.isEmCurso();
    }

}
