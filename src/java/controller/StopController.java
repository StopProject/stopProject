/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.websocket.Session;
import model.Jogador;
import model.Partida;
import view.GerarJson;

/**
 *
 * @author rafael.recalcatti
 */
public class StopController {
    
//    public static boolean SalvarRespostas(){
//                                        
//       return false;
//    }
//    
//    public String EnviaPartida(Session session){
//        
//        String nome = session.getRequestParameterMap().get("nome").get(0);
//        Configuracao.sessoes.put(nome, session);        
//
//       
//            Jogador jogador = new Jogador(nome);
//            GerarJson gj = new GerarJson();
//            Partida.getIntance().adicionarJogador(jogador);
//            String ret = "{\"funcao\":\"newJogador\",\"valor\": " + gj.getJson(jogador) + "}";
//            sendMessage(ret);
//
//            for (Session session1 : Configuracao.sessoes.values()) {
//
//                ret = "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";
//                session1.getAsyncRemote().sendText(ret);
//
//            }
//        }
//        
//    }
    
}
