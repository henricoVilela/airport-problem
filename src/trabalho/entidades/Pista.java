package trabalho.entidades;

public class Pista {
	
	private Aeronave aviao;
	
	public Pista() {}
	
	public Pista(Aeronave aviao) {
		super();
		this.aviao = aviao;
	}

	public Aeronave getAviao() {
		return aviao;
	}

	public void setAviao(Aeronave aviao) {
		this.aviao = aviao;
	}
	
	
}
