/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.gson.Gson;
import controller.Configuracao;
import controller.Jogador;
import controller.Partida;
import controller.Rodada;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael.recalcatti
 */
public class GerarJson {

public String getPartida()
        {
        Jogador jogador1 = new Jogador("RAFAEL");
        Jogador jogador2 = new Jogador("JOAO");              

        Rodada rodada1 = new Rodada('A');
        
        rodada1.adicionarJogador(jogador1);        
        rodada1.adicionarJogador(jogador2);
        
        Partida partida = new Partida(rodada1);

        partida.listarRodada();
        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(partida);
        
        return json;

   
   }

    public String getConfiguracao() {
       
        
        Gson gson = new Gson();
        Configuracao configuracao = new Configuracao();        
        String json = gson.toJson(configuracao);        
        return json;
        
    }
}
