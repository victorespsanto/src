package br.com.albacares.bluefood.domain.pagamento;

public enum StatusPagamento {
	
	Autorizado("Autorizado"),
	NaoAutorizado("Não autorizado pela instituição finaceira"),
	CartaoInvalido("Cartão Inválido ou bloqueado");
	
	String descricao;
	
	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	

}
