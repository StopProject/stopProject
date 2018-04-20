package controller;

import java.util.ArrayList;
import java.util.List;

public final class Partida {
    
    private final boolean emCurso = true;
    private final List<Rodada> rodadas1;  

    public Partida(Rodada rodada) {
        
   
        rodadas1 = new ArrayList();
        adicionarRodada(rodada);
    }
    
    public void adicionarRodada(Rodada rodada) {
        rodadas1.add(rodada);
    }
    
    public void listarRodada() {
        this.rodadas1.stream().forEach((rodada) -> {
            rodada.listarJogadores();
        });
    }
    
}
