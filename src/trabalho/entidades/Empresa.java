package trabalho.entidades;

public class Empresa {
	private String nome;
	private Double valorPago;
	private Double valorTaxa;
	
	public Empresa(String nome, Double valorPago) {
		super();
		this.nome = nome;
		this.valorPago = valorPago;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public Double getValorTaxa() {
		return valorTaxa;
	}
	public void setValorTaxa(Double valorTaxa) {
		this.valorTaxa = valorTaxa;
	}
	
	
}
