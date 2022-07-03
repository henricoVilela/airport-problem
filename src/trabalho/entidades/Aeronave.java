package trabalho.entidades;

import java.util.Random;

import trabalho.enumerados.AeronaveEstado;
import trabalho.enumerados.TanqueEstado;

public class Aeronave {
	private AeronaveEstado estado;
	private String nome;
	private Empresa empresa;
	private Integer capacidadeTanque; // em litros
	private TanqueEstado estadoTanque;
	
	
	public Aeronave() {
		
	}
	
	
	public AeronaveEstado getEstado() {
		return estado;
	}
	public void setEstado(AeronaveEstado estado) {
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Integer getCapacidadeTanque() {
		return capacidadeTanque;
	}
	public void setCapacidadeTanque(Integer capacidadeTanque) {
		this.capacidadeTanque = capacidadeTanque;
	}
	public TanqueEstado getEstadoTanque() {
		return estadoTanque;
	}
	public void setEstadoTanque(TanqueEstado estadoTanque) {
		this.estadoTanque = estadoTanque;
	}


	/**
	 * Gerar valor aletario da capacidade do tanque
	 * @return enum TanqueCapacidade com valor random
	 */
	public TanqueEstado getQuantidadeCombustivelAtual() {
		Random rand = new Random();
		Integer number = rand.nextInt(3);
		
		estadoTanque =  TanqueEstado.getByValue(number);
		
		return estadoTanque;
	}
	
	/**
	 * Pega a quantidade de combustivel no momento em litros
	 * @return
	 */
	public Double getQuantidadeCombustivelAtualLitros() {
		switch (estadoTanque) {

			case RESERVA:
				return capacidadeTanque*0.25;
	
			case MEIO_TANQUE:
				return capacidadeTanque*0.75;
	
			case QUASE_CHEIO:
				return capacidadeTanque*0.95;

		}
		
		return Double.valueOf(capacidadeTanque.toString());
	}
	

	
	
}
