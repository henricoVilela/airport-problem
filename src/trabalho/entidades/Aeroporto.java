package trabalho.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import trabalho.thread.ThreadAeronave;
import trabalho.util.Utils;

public class Aeroporto {
	private List<Aeronave> aeronaves = new ArrayList<Aeronave>();
	private List<ThreadAeronave> threadAvioes = new ArrayList<ThreadAeronave>();
	private List<Aeronave> avioesPrecisandoDecolar = new ArrayList<Aeronave>();
	private List<Aeronave> avioesPrecisandoAterrisar = new ArrayList<Aeronave>();
	private List<Aeronave> avioesPrecisandoTaxiar = new ArrayList<Aeronave>();
	
	//m-segundos
	private final static long TEMPO_ATERRISAGEM = 2000; 
	private final static long TEMPO_DECOLAGEM = 2000;
	private final static long TEMPO_TAXIANDO = 1000;
	private final static long TEMPO_ESPERA = 2000;
	
	private Pista pista = new Pista();
	
	public Aeroporto() {
		
	}
	
	public void executar() {
		sortListaThreadsAeoronave();
		
		threadAvioes.forEach((thread)->{
			thread.start();
			Utils.sleep(10);
		});
	}
	
	public void torreControle(Aeronave aviao) {
		switch (aviao.getEstado()) {

			case PRECISA_ATERRISAR:
				aviaoChegando(aviao);
			break;
	
			case PRECISA_DECOLAR:
				aviaoSaindo(aviao);
			break;
	
			case TAXIANDO:
				aviaoTaxiando(aviao);
			break;
			
			case ESTACIONADA:
				aviaoEstacionado(aviao);
			break;
	
			default:
		}
	}
	
	private void aviaoChegando(Aeronave aviao) {
		System.out.println("Aviao solicitando aterrisagem: "+aviao.getNome()+" thread: "+Thread.currentThread().getName());

		if(pista.getAviao()==null) {
			
			pista.setAviao(aviao);
			
			System.out.println("Pista vazia ---- Aviao "+aviao.getNome()+" aterrisando..");
			
			Utils.sleep(TEMPO_ATERRISAGEM);
			
			System.out.println("Aviao "+aviao.getNome()+" aterrisou ");
			
			pista.setAviao(null);
			
			if(!avioesPrecisandoAterrisar.isEmpty()) {
				avioesPrecisandoAterrisar.remove(aviao);
				escalonarFilaAterrizagem();
			}
			
			return;
			
		}else{
			System.out.println("Pista cheia ---- Aviao "+aviao.getNome()+" esperando para aterrisar..");
			addAviaoAterrisar(aviao);
			escalonarFilaAterrizagem();
		}
	}
	
	private void aviaoSaindo(Aeronave aviao) {
		System.out.println("Aviao solicitando decolagem: "+aviao.getNome()+" thread: "+Thread.currentThread().getName());

		if(pista.getAviao()==null && avioesPrecisandoAterrisar.isEmpty()) {
			
			pista.setAviao(aviao);
			System.out.println("Pista vazia ---- Aviao "+aviao.getNome()+" decolando..");
			
			Utils.sleep(TEMPO_DECOLAGEM);
			
			System.out.println("Aviao "+aviao.getNome()+" decolou ");

			pista.setAviao(null);
			
			if(!avioesPrecisandoDecolar.isEmpty()) {
				avioesPrecisandoDecolar.remove(aviao);
				escalonarFilaDecolagem();
			}
			
			return;
			
		}else if(pista.getAviao()!=null){
			System.out.println("Pista cheia ---- Aviao "+aviao.getNome()+" esperando para decolar..");
			
			addAviaoDecolar(aviao);
			escalonarFilaDecolagem();
			
		}else if(!avioesPrecisandoAterrisar.isEmpty()) { // tem aviao precisando aterrisar nesse momento
			
			System.out.println("Pista vazia, mas com avião esperando para aterrisar ----  Aviao "+aviao.getNome()+" esperando para decolar..");
			addAviaoDecolar(aviao);
			escalonarFilaAterrizagem();// chama o escalonador de fila de aterrizagem 
			escalonarFilaDecolagem();
		}
			

	}
	
	private void aviaoTaxiando(Aeronave aviao) {
		System.out.println("Aviao Tentando taxiar: "+aviao.getNome()+" thread: "+Thread.currentThread().getName());

		if(pista.getAviao()==null && avioesPrecisandoAterrisar.isEmpty() && avioesPrecisandoDecolar.isEmpty()) {//TODO
			pista.setAviao(aviao);
			System.out.println("Pista vazia ---- Aviao "+aviao.getNome()+" taxiando..");
			
			Utils.sleep(TEMPO_TAXIANDO);
			
			System.out.println("Aviao "+aviao.getNome()+" taxiou ");
			pista.setAviao(null);
			
			if(!avioesPrecisandoTaxiar.isEmpty()) {
				avioesPrecisandoTaxiar.remove(aviao);
				escalonarFilaTaxiamento();
			}
			
			return;
			
		}else if(pista.getAviao()!=null){
			System.out.println("Pista cheia ---- Aviao "+aviao.getNome()+" esperando para taxiar..");
			
			addAviaoTaxiar(aviao);
			escalonarFilaTaxiamento();
			
		}else if(!avioesPrecisandoAterrisar.isEmpty()) {
			
			System.out.println("Pista vazia, mas com avião esperando para aterrisar ----  Aviao "+aviao.getNome()+" esperando para taxiar..");
			addAviaoTaxiar(aviao);
			
			escalonarFilaAterrizagem();
			escalonarFilaTaxiamento();
		}else if(!avioesPrecisandoDecolar.isEmpty()) {
			
			System.out.println("Pista vazia, mas com avião esperando para decolar ----  Aviao "+aviao.getNome()+" esperando para taxiar..");
			addAviaoTaxiar(aviao);
			
			escalonarFilaDecolagem(); 
			escalonarFilaTaxiamento();
		}

	}
	
	private void aviaoEstacionado(Aeronave aviao) {
		System.out.println("Avião "+aviao.getNome()+" esta estacionado.");
	}
	
	/**
	 * Metodo que seleciona um avião da fila para taxiar
	 * @throws InterruptedException
	 */
	private void escalonarFilaTaxiamento(){
		
		if(pista.getAviao()==null) {
			if(!avioesPrecisandoTaxiar.isEmpty()) {
				Aeronave aviaoParaTaxiar = avioesPrecisandoTaxiar.stream().findFirst().get();
				System.out.println("escalonador: "+aviaoParaTaxiar.getNome());
				aviaoTaxiando(aviaoParaTaxiar);
			}else 
				return;
			
		}else{
			Utils.sleep(TEMPO_ESPERA);
			escalonarFilaTaxiamento();
		}
		
		
	}
	
	/**
	 * Metodo para selecionar um avião da fila para decolagem
	 * @throws InterruptedException
	 */
	private void escalonarFilaDecolagem(){
		
		if(pista.getAviao()==null) {
			
			if(!avioesPrecisandoDecolar.isEmpty()) {
				Aeronave aviaoParaDecolar = avioesPrecisandoDecolar.stream().findFirst().get();
				System.out.println("escalonador: "+aviaoParaDecolar.getNome());
				aviaoSaindo(aviaoParaDecolar);
			}else 
				return;
			

		}else{
			Utils.sleep(TEMPO_ESPERA);
			escalonarFilaDecolagem();
		}
		
		
	}
	
	/**
	 * Metodo para selecionar avião da fila para aterrizagem
	 * @throws InterruptedException
	 */
	private void escalonarFilaAterrizagem(){
		
		if(pista.getAviao()==null) {
			
			if(!avioesPrecisandoAterrisar.isEmpty()) {
				Aeronave aviaoInicial = avioesPrecisandoAterrisar.stream().findFirst().get();
				Utils.sortListaAeoronaveByCombustivel(avioesPrecisandoAterrisar);
				Aeronave aviaoParaAterrisar = avioesPrecisandoAterrisar.stream().findFirst().get();
				
				if(aviaoInicial != aviaoParaAterrisar) {
					Double valorEmpresa1 = aviaoInicial.getEmpresa().getValorPago();
					Double valorEmpresa2 = aviaoParaAterrisar.getEmpresa().getValorPago();
					
					aviaoParaAterrisar.getEmpresa().setValorTaxa((valorEmpresa1 - valorEmpresa2)*0.08);
					
					System.err.println("Taxa de 8% no valor de "+aviaoParaAterrisar.getEmpresa().getValorTaxa()+" para a empresa "
							+ ""+ aviaoParaAterrisar.getEmpresa().getNome());
				}
				
				System.out.println("escalonador: "+aviaoParaAterrisar.getNome());
				aviaoChegando(aviaoParaAterrisar);
			}else 
				return;
			

		}else{
			Utils.sleep(TEMPO_ESPERA);
			escalonarFilaAterrizagem();
		}
		
		
	}

	
	public void setAeronaves(List<Aeronave> aeronaves) {
		
		
		sortListaAvioes(aeronaves);

		aeronaves.forEach(aviao->{
			
			ThreadAeronave thread = new ThreadAeronave(aviao,this);
			int index = aeronaves.indexOf(aviao)+1;
			
			//Devido ao maximo de prioridade das thread 
			//? - True
			//: - False
			thread.setPriority((index <= 10) ? index : 10);
			
			this.threadAvioes.add(thread);
		});
		
		this.aeronaves = aeronaves;
		
		
	}
	
	private void sortListaAvioes(List<Aeronave> aeronaves) {
		Comparator<Aeronave> comparetor = new Comparator<Aeronave>() {
			
			 @Override
		     public int compare(Aeronave  aeronave, Aeronave  aeronave2){
		         return aeronave.getEmpresa().getValorPago().compareTo(aeronave2.getEmpresa().getValorPago());
		     }
		};
		
		Collections.sort(aeronaves, comparetor);
		 
	}
	
	private void sortListaThreadsAeoronave() {
		Comparator<ThreadAeronave> comparetor = new Comparator<ThreadAeronave>() {
			
			 @Override
		     public int compare(ThreadAeronave  aeronave, ThreadAeronave  aeronave2){
				 Integer v1 = Integer.valueOf(aeronave.getPriority());
				 Integer v2 = Integer.valueOf(aeronave2.getPriority());
				 
				 return v1.compareTo(v2);
		     }
		};
		
		Collections.sort(this.threadAvioes, comparetor.reversed());
		 
	}
	
	private void addAviaoTaxiar(Aeronave aviao) {
		if(!avioesPrecisandoTaxiar.contains(aviao))
			avioesPrecisandoTaxiar.add(aviao);
	}
	
	private void addAviaoDecolar(Aeronave aviao) {
		if(!avioesPrecisandoDecolar.contains(aviao))
			avioesPrecisandoDecolar.add(aviao);
	}
	
	private void addAviaoAterrisar(Aeronave aviao) {
		if(!avioesPrecisandoAterrisar.contains(aviao))
			avioesPrecisandoAterrisar.add(aviao);
	}
	
	public List<Aeronave> getAeronaves() {
		return aeronaves;
	}
	
	public List<ThreadAeronave> getThreadAviao() {
		return threadAvioes;
	}
	public void setThreadAviao(List<ThreadAeronave> threadAviao) {
		this.threadAvioes = threadAviao;
	}
	public Pista getPista() {
		return pista;
	}
	public void setPista(Pista pista) {
		this.pista = pista;
	}
	

	
}
