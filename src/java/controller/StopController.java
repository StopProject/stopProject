/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import javax.websocket.Session;
import model.Jogador;
import model.Partida;
import view.GerarJson;
import websockets.WebSocket;

/**
 *
 * @author rafael.recalcatti
 */
public class StopController {

    public String getMessage(websockets.WebSocket websocket, String message) {

        System.out.println(message.getClass() + " " + message);

        if (message.contains("getPartida")) {

            GerarJson gj = new GerarJson();
            return "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";

        } else {
            
            if (message.contains("newRodada")) {

                GerarJson gj = new GerarJson();

                for (websockets.WebSocket session1 : Configuracao.sessoes.values()) {
                    try {
                        return "{\"funcao\":\"newRodada\",\"valor\": " + gj.getJson(Partida.getIntance().adicionarRodada('S')) + "}";
                    } catch (Exception e) {
                        return "{\"funcao\":\"erro\",\"valor\":{\"codigo\":2, \"message\":\"ERRO INTERNO\"}}";
                    }
                    
                }
            } else if (message.contains("putRespostas")) {

                GerarJson gj = new GerarJson();
                Retorno data = new Gson().fromJson(message, Retorno.class);
                               
                for(int i = 0 ; i < Partida.getIntance().getRodadas().size(); i++){
                    
                    System.err.println("::"+Partida.getIntance().getJogadores()); 
                }
                Partida.getIntance().getRodadas().get(0).getLinhaPlanilha().get(0).setRepostas(data.valor);
                return "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";

            }

            return "{\"funcao\":\"erro\",\"valor\":{\"codigo\":2, \"message\":\"ERRO INTERNO\"}}";

        }

    }
    
    public String setOpen(websockets.WebSocket session){
              
     
        String nome = session.session.getRequestParameterMap().get("nome").get(0);
        Configuracao.sessoes.put(nome, session);

        if (nome.isEmpty() || nome.equals("")) {

            return "{\"funcao\":\"erro\",\"valor\":{\"codigo\":2, \"message\":\"nickname em branco\"}}";

        } else if (Partida.getIntance().equals(nome)) {

            return "{\"funcao\":\"erro\",\"valor\":{\"codigo\":1, \"message\":\"nickname ja utilizado\"}}";

        } else {

            Jogador jogador = new Jogador(nome);
            GerarJson gj = new GerarJson();
            Partida.getIntance().adicionarJogador(jogador);
                       
            return "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";
              
            
        }

    }
}

class Retorno {

    public String funcao;
    public String[] valor;

    public Retorno(String funcao, String[] valor) {

        this.funcao = funcao;
        this.valor = valor;
    }

}

