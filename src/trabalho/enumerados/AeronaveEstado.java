package trabalho.enumerados;

public enum AeronaveEstado {
	ESTACIONADA("Estacionada"),
	TAXIANDO("Taxiando"),
	PRECISA_ATERRISAR("Precisa aterrisar"),
	PRECISA_DECOLAR("Precisa decolar");
	
	private String descricao;
			
	AeronaveEstado(String descricao) {
		this.descricao = descricao;
	}
	 
    public String getDescricao() {
        return descricao;
    }
}
