/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;


/**
 *
 * @author rafael.recalcatti
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
          Gson gson = new Gson();
          String json = "{\"funcao\":\"putRespostas\",\"valor\":[\"valor1\",\"FTEC\",\"valor 3\", \"DAL-HE INTER\"]}";
          Retorno data = new Gson().fromJson(json,Retorno.class);
        
        System.out.println(data.valor[1]);
         

        

    }
    
}
class Retorno{
 public String funcao;
 public String[] valor;

    public Retorno(String funcao, String[] valor) {
        this.funcao = funcao;
        this.valor = valor;
    }
 
}
