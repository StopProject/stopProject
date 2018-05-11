package controller;

import java.util.ArrayList;
import java.util.List;

public final class Rodada {

    private char letra;
    private List<LinhaPlanilha> linhaPlanilha;

    public Rodada(char letra, List<Jogador> jogadores, int qtdCategorias) {
        
        this.letra = letra;
        linhaPlanilha = new ArrayList<>();
        
        for(Jogador jogador : jogadores){
          linhaPlanilha.add(new LinhaPlanilha(jogador));  
        }
        
            
    }
       
   public void addLinhaPlanilha(){
       
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
