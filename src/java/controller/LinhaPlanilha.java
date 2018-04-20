package controller;


public class LinhaPlanilha extends Jogador{
	
	public LinhaPlanilha(String nome) {
		super(nome);
		
	}

	private String repostas;
	private int pontuacao;
	private boolean jogando = true;
	
	
	public String getRepostas() {
		return repostas;
	}
	
	public void setRepostas(String resposta){
		this.repostas = resposta;
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

}
