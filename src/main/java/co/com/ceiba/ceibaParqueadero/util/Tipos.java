package co.com.ceiba.ceibaParqueadero.util;

public enum Tipos {
	TIPO_CARRO("carro"),
    TIPO_MOTO("moto");
	
	private final String tipo;
	
	private Tipos(final String tipo) {
	        this.tipo = tipo;
	 }

    public String getTipo() {
        return tipo;
    }
	
	public static boolean contains(String s){
		for(Tipos tipo:values()){
			if(tipo.getTipo().equals(s)) {
				return true;
			}
		}
		return false;
	} 

}
