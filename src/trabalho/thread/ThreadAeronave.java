package trabalho.thread;

import trabalho.entidades.Aeronave;
import trabalho.entidades.Aeroporto;

public class ThreadAeronave extends Thread{
	
	private final Aeronave aeronave;
	private final Aeroporto aeroporto;

	
	public ThreadAeronave(Aeronave aeronave, Aeroporto aeroporto) {
		super();
		this.aeronave = aeronave;
		this.aeroporto = aeroporto;
	}
	
	
	@Override
    public void run() {
		

		String nameThread = Thread.currentThread().getName();
		System.out.println("PRIORIDADE: "+this.getPriority()+" | Aeronave: "+ aeronave.getNome()+" -> Empresa: "
				+ ""+aeronave.getEmpresa().getNome()+ " - Estado: "+ aeronave.getEstado()+" - Quantidade Combustivel: "
						+ ""+aeronave.getQuantidadeCombustivelAtual()+" > "+aeronave.getQuantidadeCombustivelAtualLitros()+"L thread: "+nameThread);
		
		
		aeroporto.torreControle(aeronave);
		
	}
	
}
