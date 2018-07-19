package controller;

import java.util.Observable;
import model.Jogador;


public class LinhaPlanilha extends Observable{
	
	
	private String[] repostas;
	private int pontuacao;
	private boolean jogando = false;
        private Jogador jogador;
		
	public LinhaPlanilha(Jogador jogador){
            
            this.jogador = jogador;
            this.repostas = new String[Configuracao.getInstance().getCategorias().size()];
        }
        
	public int getPontuacao() {
		return pontuacao;
	}
	
	public void setRepostas(int pontuacao){
		this.pontuacao = pontuacao;
	}
	
	public boolean getJogando() {
		return jogando;
	}
	
	public void setJogando(){
		this.jogando = false;
	}

    /**
     * @return the repostas
     */
    public String[] getRepostas() {
        return repostas;
    }

    /**
     * @param repostas the repostas to set
     * @param letra
     */
    public synchronized void setRepostas(String[] repostas, char letra) {
        
        this.repostas = repostas;
        //if(this.validaRespostas(letra)){
        if(this.repostas[0] != null &&this.repostas[1] != null &&this.repostas[2] != null &&
                this.repostas[3] != null &&this.repostas[4] != null){
            this.jogando = false;            
            this.setChanged();
            System.out.println("notificando");
            this.notifyObservers();
        }
    }

    private boolean validaRespostas(char letra) {
      
        int count = 0;
        boolean _ret = false;
        try {
            
            for (String reposta : this.repostas) {
                
                if (reposta != null && reposta.charAt(0) == letra && !reposta.isEmpty()) {                    
                    this.pontuacao += 10;
                    count++;
                }
            }
            _ret = count == 5;
            
        } catch (Exception ex) {
            
                _ret = false;
        }
        return _ret;
        
    }
    
   

}
