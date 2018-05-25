/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.Session;

/**
 *
 * @author rafael.recalcatti
 */
public class Configuracao {
    
    
    private List<String> categorias;
    private int minJogadores;
    private int maxJogadores;
    private int limiteTempo;
    private int qtdRodadas;
    private static Configuracao configuracao;
    public static Map<String,Session> sessoes = new HashMap<String, Session>();
    
    
    private Configuracao()
    {
        minJogadores = 2;
        maxJogadores = 5;
        limiteTempo = 60;
        qtdRodadas =  10;
        categorias = new ArrayList<>();
        categorias.add("Nome");
        categorias.add("CEP");
        categorias.add("Fruta");
        categorias.add("Carro");
        categorias.add("Cor");
    }
    
    public static Configuracao getInstance(){
        
        if (configuracao == null) {

            configuracao = new Configuracao();
        }
        return configuracao;
    }
    
    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategoria(List<String> categoria) {
        this.categorias = categoria;
    }

    public int getMinJogadores() {
        return minJogadores;
    }

    public void setMinJogadores(int minJogadores) {
        this.minJogadores = minJogadores;
    }

    public int getLimiteTempo() {
        return limiteTempo;
    }

    public void setLimiteTempo(int limiteTempo) {
        this.limiteTempo = limiteTempo;
    }

    public int getQtdRodadas() {
        return qtdRodadas;
    }

    public void setQtdRodadas(int qtdRodadas) {
        this.qtdRodadas = qtdRodadas;
    }
    
    
}
