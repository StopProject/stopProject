/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockets;

import com.google.gson.Gson;
import controller.Configuracao;
import controller.LinhaPlanilha;
import controller.StopController;
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

    public Session session;
    public Jogador jogador;

    @OnMessage
    public void onMessage(String message) {
        if (message.contains("putResposta")) {
            String msg = StopController.GetInstance().getMessage(this, message);
        for (WebSocket ws : Configuracao.sessoes.values()) {
            try {
                ws.sendMessage(msg);
            } catch (Exception e) {}
        }    
        } else {
        sendMessage(StopController.GetInstance().getMessage(this, message));      
        }
    }

    @OnOpen
    public void onOpen(Session session) {       
        this.session = session;
        String msg = (StopController.GetInstance().setOpen(this)); 
        for (WebSocket ws : Configuracao.sessoes.values()) {
            try {
                ws.sendMessage(msg);
            } catch (Exception e) {}
        }
    }

    public void sendMessage(String m) {
        session.getAsyncRemote().sendText(m);
    }
}

/*
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
*/