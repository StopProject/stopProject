package model;

import controller.LinhaPlanilha;
import controller.StopController;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public final class Rodada {

    private char letra;
    private boolean emCurso = false;
    private List<LinhaPlanilha> linhaPlanilha;

    public Rodada(char letra, List<Jogador> jogadores, int qtdCategorias) {

        this.letra = letra;
        this.emCurso = true;
        linhaPlanilha = new ArrayList<>();

        jogadores.stream().forEach((jogador) -> {
            
            LinhaPlanilha linha = new LinhaPlanilha(jogador);
            linha.addObserver(StopController.GetInstance());
            linhaPlanilha.add(linha);

        });
    }

    public void addLinhaPlanilha(LinhaPlanilha linhaPlanilha) {

        this.linhaPlanilha.add(linhaPlanilha);

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

    /**
     * @return the linhaPlanilha
     */
    public List<LinhaPlanilha> getLinhaPlanilha() {
        return linhaPlanilha;
    }

    /**
     * @param linhaPlanilha the linhaPlanilha to set
     */
    public void setLinhaPlanilha(List<LinhaPlanilha> linhaPlanilha) {
        this.linhaPlanilha = linhaPlanilha;
    }

    /**
     * @return the emCurso
     */
    public boolean isEmCurso() {
        return emCurso;
    }

    /**
     * @param emCurso the emCurso to set
     */
    public void setEmCurso(boolean emCurso) {
        this.emCurso = emCurso;
    }

   
}
