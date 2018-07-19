/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import model.Jogador;
import model.Partida;
import model.Rodada;
import sun.security.jca.GetInstance;
import view.GerarJson;
import websockets.WebSocket;

/**
 *
 * @author rafael.recalcatti
 */
public class StopController implements Observer {

    private static StopController instancia;

    private StopController() {

    }

    public static StopController GetInstance() {
        if (instancia == null) {
            instancia = new StopController();
        }
        return instancia;
    }

    public void paraTudo() {
        GerarJson gj = new GerarJson();
        String msg = "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}"; 
        for (WebSocket ws : Configuracao.sessoes.values()) {
            try {
                ws.sendMessage(msg);
            } catch (Exception e) {}
        }
    }
    public String getMessage(websockets.WebSocket websocket, String message) {

        String _return = null;
        GerarJson gj = new GerarJson();
        Retorno data = new Gson().fromJson(message, Retorno.class);

        System.out.println("EXIT::" + message.getClass() + " " + message);

        if (message.contains("getConfiguracoes")) {

            _return = "{\"funcao\":\"getConfiguracoes\",\"valor\": " + gj.getJson(Configuracao.getInstance()) + "}";

        } else if (message.contains("getPartida")) {

            _return = "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";

        } else if (message.contains("putRespostas")) {

            if ((Partida.getIntance().getRodadas().size() > 0 ) && (!Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size() - 1).isEmCurso())) {

                _return = "{\"funcao\":\"erro\",\"valor\":{\"codigo\":3, \"message\":\"RODADA FINALIZADA\"}}";

            } else if (Partida.getIntance().getJogadores().size() > 1) {

                System.err.println("DONO SESSAO::" + Configuracao.sessoes.get(websocket.session.getId()).session.getRequestParameterMap().get("nome"));
                System.err.println("QTD RODADAS::" + Partida.getIntance().getRodadas().size());
                System.err.println("RODADA ATUAL::" + Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size() - 1));
                System.err.println("RODADA EM CURSO::" + Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size() - 1).isEmCurso());
                System.err.println("QTD JOGADORES::" + Partida.getIntance().getJogadores().size());
                System.err.println("QTD LINHAS PLANILHA::" + Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size() - 1).getLinhaPlanilha().size());

                String nomeJogador = Configuracao.sessoes.get(websocket.session.getId()).session.getRequestParameterMap().get("nome").get(0);

                for (int i = 0; i < Partida.getIntance().getJogadores().size(); i++) {

                    try {

                        if (Partida.getIntance().getJogadores().get(i).getNome().equals((nomeJogador))) {
                            List<Rodada> rodadas = Partida.getIntance().getRodadas();
                            System.out.println("rodadas -> " + rodadas + "  size:" + rodadas.size());
                            Rodada get = rodadas.get(rodadas.size() - 1);
                            System.out.println("get ->" + get + "   i->" + i);
                            get.getLinhaPlanilha().get(i).setRepostas(data.valor, Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size() - 1).getLetra());

                            System.err.println("CARREGOU RESPOSTAS DO JOGADOR " + nomeJogador);
                            System.err.println("PONTOS DO JOGADOR " + Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size() - 1).getLinhaPlanilha().get(i).getPontuacao());
                        }
                    } catch (Exception ex) {

                        //   System.err.println("NUMERO RESPOSTAS::"+Partida.getIntance().);
                    }
                }
                System.err.println("{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}");
                _return = "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";

            } else {
                _return = "{\"funcao\":\"erro\",\"valor\":{\"codigo\":4, \"message\":\"AGUARDE OUTRO JOGADOR\"}}";
            }

        }
        return _return;
    }

    public String setOpen(websockets.WebSocket websocket) {

        String nome = websocket.session.getRequestParameterMap().get("nome").get(0);
        System.err.println("CHAVE SESSAO::" + nome);
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

    @Override
    public void update(Observable o, Object arg) {

        Partida.getIntance().getRodadas().get(Partida.getIntance().getRodadas().size() - 1).setEmCurso(false);
        System.out.println("->STOP::::" + o);
    
        String msg =  "{\"funcao\":\"erro\",\"valor\":{\"codigo\":3, \"message\":\"RODADA FINALIZADA\"}}";
        for (WebSocket ws : Configuracao.sessoes.values()) {
            try {
                ws.sendMessage(msg);
            } catch (Exception e) {}
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
