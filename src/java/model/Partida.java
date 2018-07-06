package model;

import controller.Configuracao;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Partida {

    private boolean emCurso = false;
    private List<Rodada> rodadas;
    private static Partida partida;
    private List<Jogador> jogadores;

    
    
    public static Partida getIntance(){

        if (partida == null) {

            partida = new Partida();
        }
        return partida;
    }

    public void adicionarJogador(Jogador jogador) {

        
        char letras[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','W','Y','Z'};
        
        
        getJogadores().add(jogador);
        
        if(jogadores.size() >= Configuracao.getInstance().getMinJogadores()){
            
            if(!isEmCurso()){  
                
                  char letraRodada = letras[0];//new Random().nextInt(25)];               
                  getRodadas().add(new Rodada(letraRodada, jogadores, 0));
                  setEmCurso(true);
            }
                      
        }
        
    }
      
    private Partida() {
        
        rodadas = new ArrayList();
        jogadores = new ArrayList();
    }

    public Rodada adicionarRodada(char letra) {      
        
        Rodada ret = new Rodada(letra, jogadores, Configuracao.getInstance().getQtdRodadas());
        getRodadas().add(ret);
        return ret;
    }

    /**
     * @return the jogadores
     */
    public List<Jogador> getJogadores() {
        return jogadores;
    }
    

    public boolean equals(String arg) {     
        
        return jogadores.stream().anyMatch((jogador) -> (jogador.getNome().equals(arg)));
    }

    /**
     * @return the rodadas
     */
    public List<Rodada> getRodadas() {     
        return rodadas;
    }

    /**
     * @param rodadas the rodadas to set
     */
    public void setRodadas(List<Rodada> rodadas) {
        
        this.rodadas = rodadas;
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
