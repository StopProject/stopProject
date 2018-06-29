package model;

import controller.LinhaPlanilha;
import java.util.ArrayList;
import java.util.List;

public final class Rodada {

    private char letra; 
    private List<LinhaPlanilha> linhaPlanilha;

    public Rodada(char letra, List<Jogador> jogadores, int qtdCategorias) {
        
        this.letra = letra;        
        linhaPlanilha = new ArrayList<>();
        
        jogadores.stream().forEach((jogador) -> {
            linhaPlanilha.add(new LinhaPlanilha(jogador));
        });
        
            
    }
       
   public void addLinhaPlanilha(LinhaPlanilha linhaPlanilha){
       
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
    

}
