/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockets;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.jboss.weld.servlet.SessionHolder;

/**
 *
 * @author rafael.recalcatti
 */
@ServerEndpoint("/websocket")
public class WebSocket {

    Session session;

    @OnMessage
    public void onMessage(String message) {

        System.out.println(message);
        //this.sendMessage("FALA AI");
        
        //System.err.println();

    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        ThreadSend t = new ThreadSend(session);
        System.out.println("aqui");
        t.start();
        System.out.println("depois");
    }

    public void sendMessage(String m) {
        session.getAsyncRemote().sendText(m);
    }
}
class ThreadSend extends Thread {
    private Session sessao;
    public ThreadSend(Session s){
        this.sessao = s;
     
    }
    
    public void run(){
        while (true) {
        //System.out.println("asdasd");    
            sessao.getAsyncRemote().sendText("envio "+Calendar.getInstance().getTime());
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}