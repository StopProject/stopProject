package controller;

import java.util.List;

public final class Rodada {

    private char letra;

    public Rodada(char letra, List<Jogador> jogadores, int qtdCategorias) {
        this.letra = letra;

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
