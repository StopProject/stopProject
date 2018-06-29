/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.util.List;
import model.Jogador;
import model.Partida;
import model.Rodada;
import view.GerarJson;

/**
 *
 * @author rafael.recalcatti
 */
public class StopController {

    public String getMessage(websockets.WebSocket websocket, String message) {

        System.out.println("EXIT::"+message.getClass() + " " + message);

         if (message.contains("getConfiguracoes")) {

            GerarJson gj = new GerarJson();
            return "{\"funcao\":\"getConfiguracoes\",\"valor\": " + gj.getJson(Configuracao.getInstance()) + "}";

        } else 
        if (message.contains("getStop")) {

            return "{\"funcao\":\"erro\",\"valor\":{\"STOP\"}}";

        } else 
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
                
                if(Partida.getIntance().getJogadores().size()> 1){
                    
                System.err.println("DONO SESSAO::"+Configuracao.sessoes.get(websocket.session.getId()).session.getRequestParameterMap().get("nome"));
                System.err.println("QTD RODADAS::"+Partida.getIntance().getRodadas().size());                               
                System.err.println("RODADA ATUAL::"+Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size()-1));
                System.err.println("QTD JOGADORES::"+Partida.getIntance().getJogadores().size());
                System.err.println("QTD LINHAS PLANILHA::"+Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size()-1).getLinhaPlanilha().size());
                String nomeJogador = Configuracao.sessoes.get(websocket.session.getId()).session.getRequestParameterMap().get("nome").get(0);
                
                for(int i = 0 ; i < Partida.getIntance().getJogadores().size(); i++){                                         
                   
                    if(Partida.getIntance().getJogadores().get(i).getNome().equals((nomeJogador)))
                    {                                         
                        List<Rodada> rodadas = Partida.getIntance().getRodadas();
                        System.out.println("rodadas ->"+rodadas+"  size:"+rodadas.size());
                        Rodada get = rodadas.get(rodadas.size()-1);
                        System.out.println("get ->"+get+"   i->"+i);
                        get.getLinhaPlanilha().get(i).setRepostas(data.valor);
                        System.err.println("CARREGOU RESPOSTAS DO JOGADOR "+nomeJogador);
                    }
                                      
                }
                }
                System.err.println("{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}");
                return "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";

            }

                return "{\"funcao\":\"erro\",\"valor\":{\"codigo\":2, \"message\":\"ERRO INTERNO\"}}";

        }

    }
    
    public String setOpen(websockets.WebSocket websocket){
              
     
        String nome = websocket.session.getRequestParameterMap().get("nome").get(0);
        System.err.println("CHAVE SESSAO::"+nome);
        Configuracao.sessoes.put(websocket.session.getId(), websocket);

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

