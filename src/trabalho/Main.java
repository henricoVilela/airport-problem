package trabalho;

import java.util.ArrayList;
import java.util.List;

import trabalho.entidades.Aeronave;
import trabalho.entidades.Aeroporto;
import trabalho.entidades.Empresa;
import trabalho.enumerados.AeronaveEstado;

public class Main {

	public static void main(String[] args) {
		
		List<Aeronave> avioes = new ArrayList<Aeronave>();
		
		Empresa empresaAzul = new Empresa("AZUL", 2500.00);
		Empresa empresaGol = new Empresa("GOL", 5500.00);
		Empresa empresaTam = new Empresa("TAM", 3500.00);
		
		Aeronave aviao1 = new Aeronave();
		aviao1.setCapacidadeTanque(100);
		aviao1.setEmpresa(empresaAzul);
		aviao1.setEstado(AeronaveEstado.PRECISA_ATERRISAR);
		aviao1.setNome("AZUL - 1");
		
		Aeronave aviao2 = new Aeronave();
		aviao2.setCapacidadeTanque(100);
		aviao2.setEmpresa(empresaAzul);
		aviao2.setEstado(AeronaveEstado.PRECISA_DECOLAR);
		aviao2.setNome("AZUL - 2");
		
		Aeronave aviao3 = new Aeronave();
		aviao3.setCapacidadeTanque(100);
		aviao3.setEmpresa(empresaGol);
		aviao3.setEstado(AeronaveEstado.PRECISA_DECOLAR);
		aviao3.setNome("GOL - 1");
		
		Aeronave aviao4 = new Aeronave();
		aviao4.setCapacidadeTanque(100);
		aviao4.setEmpresa(empresaTam);
		aviao4.setEstado(AeronaveEstado.PRECISA_ATERRISAR);
		aviao4.setNome("TAM - 1");
		
		Aeronave aviao5 = new Aeronave();
		aviao5.setCapacidadeTanque(100);
		aviao5.setEmpresa(empresaGol);
		aviao5.setEstado(AeronaveEstado.PRECISA_DECOLAR);
		aviao5.setNome("GOL - 2");
		
		Aeronave aviao6 = new Aeronave();
		aviao6.setCapacidadeTanque(100);
		aviao6.setEmpresa(empresaTam);
		aviao6.setEstado(AeronaveEstado.PRECISA_ATERRISAR);
		aviao6.setNome("TAM - 2");
		
		Aeronave aviao7 = new Aeronave();
		aviao7.setCapacidadeTanque(100);
		aviao7.setEmpresa(empresaTam);
		aviao7.setEstado(AeronaveEstado.TAXIANDO);
		aviao7.setNome("TAM - 3");
		
		Aeronave aviao8 = new Aeronave();
		aviao8.setCapacidadeTanque(100);
		aviao8.setEmpresa(empresaTam);
		aviao8.setEstado(AeronaveEstado.TAXIANDO);
		aviao8.setNome("TAM - 4");
		
		Aeronave aviao9 = new Aeronave();
		aviao9.setCapacidadeTanque(100);
		aviao9.setEmpresa(empresaAzul);
		aviao9.setEstado(AeronaveEstado.PRECISA_ATERRISAR);
		aviao9.setNome("AZUL - 3");
		
		//avioes.add(aviao1);
		avioes.add(aviao2);
		avioes.add(aviao3);
		//avioes.add(aviao4);
		avioes.add(aviao5);
		avioes.add(aviao6);	
		avioes.add(aviao7);	
		//avioes.add(aviao8);	
		avioes.add(aviao9);	
		
		Aeroporto aeroporto = new Aeroporto();
		aeroporto.setAeronaves(avioes);
		
		
		aeroporto.executar();
		
	}

}
