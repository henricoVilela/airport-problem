package trabalho.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import trabalho.entidades.Aeronave;

public class Utils {
	
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.err.println("Thread-error: "+Thread.currentThread().getName());
			e.printStackTrace();
		}
	}
	
	public static void sortListaAeoronaveByCombustivel(List<Aeronave> aeronaves) {
		Comparator<Aeronave> comparetor = new Comparator<Aeronave>() {
			
			 @Override
		     public int compare(Aeronave  aeronave, Aeronave  aeronave2){
				 return aeronave.getQuantidadeCombustivelAtualLitros().compareTo(aeronave2.getQuantidadeCombustivelAtualLitros());
		     }
		};
		
		Collections.sort(aeronaves, comparetor);
		 
	}
}
