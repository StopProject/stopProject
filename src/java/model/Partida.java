package model;

import controller.Configuracao;
import model.Jogador;
import java.util.ArrayList;
import java.util.List;

public final class Partida {

    private boolean emCurso = true;
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

        getJogadores().add(jogador);
        if(jogadores.size() >= Configuracao.getInstance().getMinJogadores()){
            rodadas.add(new Rodada('A', jogadores, 0));
        }
        
    }
      
    private Partida() {
        rodadas = new ArrayList();
        jogadores = new ArrayList();
    }

    public Rodada adicionarRodada(char letra) {
        Rodada ret = new Rodada(letra, jogadores, Configuracao.getInstance().getQtdRodadas());
        rodadas.add(ret);
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
}
