/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockets;

import com.google.gson.Gson;
import controller.Configuracao;
import controller.LinhaPlanilha;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import model.Jogador;
import model.Partida;
import model.Rodada;
import view.GerarJson;

/**
 *
 * @author rafael.recalcatti
 */
@ServerEndpoint("/websocket")
public class WebSocket {

    Session session;
    Jogador jogador;

    @OnMessage
    public void onMessage(String message) {

        System.out.println(message.getClass() + " " + message);

        if (message.contains("getPartida")) {
            GerarJson gj = new GerarJson();

            String ret = "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";
            sendMessage(ret);

        }
        if (message.contains("newRodada")) {
            GerarJson gj = new GerarJson();

            String ret = "{\"funcao\":\"newRodada\",\"valor\": " + gj.getJson(Partida.getIntance().adicionarRodada('S')) + "}";
            for (Session session1 : Configuracao.sessoes.values()) {
                try {
                    session1.getAsyncRemote().sendText(ret);
                } catch (Exception e) {
                }
            }
        }
        if (message.contains("putRespostas")) {

            GerarJson gj = new GerarJson();
            Retorno data = new Gson().fromJson(message, Retorno.class);
            
            Partida.getIntance().getRodadas().get(0).getLinhaPlanilha().get(0).setRepostas(data.valor);
            
            String ret = "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";
            sendMessage(ret);

        }

    }

    @OnOpen
    public void onOpen(Session session) {

        this.session = session;
        //ThreadSend t = new ThreadSend(session);        
        //t.start();

        String nome = this.session.getRequestParameterMap().get("nome").get(0);
        Configuracao.sessoes.put(nome, session);

        if (nome.isEmpty() || nome.equals("")) {

            sendMessage("{\"funcao\":\"erro\",\"valor\":{\"codigo\":2, \"message\":\"nickname em branco\"}}");

        } else if (Partida.getIntance().equals(nome)) {

            sendMessage("{\"funcao\":\"erro\",\"valor\":{\"codigo\":1, \"message\":\"nickname ja utilizado\"}}");

        } else {

            Jogador jogador = new Jogador(nome);
            GerarJson gj = new GerarJson();
            Partida.getIntance().adicionarJogador(jogador);
            String ret = "{\"funcao\":\"newJogador\",\"valor\": " + gj.getJson(jogador) + "}";
            sendMessage(ret);

            for (Session session1 : Configuracao.sessoes.values()) {

                ret = "{\"funcao\":\"getPartida\",\"valor\": " + gj.getJson(Partida.getIntance()) + "}";
                try {
                    session1.getAsyncRemote().sendText(ret);
                } catch (Exception e) {
                }

            }
        }

    }

    public void sendMessage(String m) {
        session.getAsyncRemote().sendText(m);
    }
}

class ThreadSend extends Thread {

    private Session sessao;

    public ThreadSend(Session s) {
        this.sessao = s;

    }

    public void run() {

        while (true) {
            //System.out.println("asdasd");    
            sessao.getAsyncRemote().sendText("envio " + Calendar.getInstance().getTime());
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
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
