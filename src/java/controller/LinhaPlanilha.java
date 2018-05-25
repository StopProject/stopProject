package controller;

import model.Jogador;


public class LinhaPlanilha{
	
	
	private String[] repostas;
	private int pontuacao;
	private boolean jogando = true;
        private Jogador jogador;
		
	public LinhaPlanilha(Jogador jogador ){
            
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
     */
    public void setRepostas(String[] repostas) {
        this.repostas = repostas;
    }

}
