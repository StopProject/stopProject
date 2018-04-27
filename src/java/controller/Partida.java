package controller;

import java.util.ArrayList;
import java.util.List;

public final class Partida {

    private boolean emCurso = true;
    private List<Rodada> rodadas;
    private static Partida partida;
    private List<Jogador> jogadores;

    public static Partida getIntance() {
      
        if (partida == null) {
            
            partida = new Partida();
        }
        return partida;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }
    
    private Partida() {           
        rodadas = new ArrayList(); 
        jogadores = new ArrayList(); 
    }
    
    
    
    public void adicionarRodada(Rodada rodada) {
        rodadas.add(rodada);
    }
    
  
    //public void listarJogadores() {

       // this.jogadores.stream().forEach((s) -> {
       //     System.out.println(s.getNome());
      //  });
   // }
    
}
