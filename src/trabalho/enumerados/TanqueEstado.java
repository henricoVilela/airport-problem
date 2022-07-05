package trabalho.enumerados;

import java.util.Arrays;

public enum TanqueEstado {
	RESERVA(0,"Reserva"),
	MEIO_TANQUE(1,"Meio Tanque"),
	QUASE_CHEIO(2, "Quase Cheio");
	
	private final Integer valor;
    private final String descricao;

    TanqueEstado(int valor, String descricao) {
         this.valor = valor;
         this.descricao = descricao;
     }
     
    public int getValor() {
        return this.valor;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public static final TanqueEstado getByValue(Integer value){
    	
        return Arrays.stream(TanqueEstado.values()).filter(enumRole -> enumRole.valor.equals(value)).findFirst().orElse(null);
    }
}
