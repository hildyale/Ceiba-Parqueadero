package co.com.ceiba.CeibaParqueadero.Util;

public abstract class Constants {

	public static final int MAX_CARROS = 20;
    public static final int MAX_MOTOS = 10;
    public static final String RESTRICCION_PRIMERA_LETRA = "A";
    public static final String RESTRICCION_DIA_UNO = "Domingo";
    public static final String RESTRICCION_DIA_DOS = "Lunes";
    public static final double VALOR_HORA_CARRO = 1000;
    public static final double VALOR_HORA_MOTO = 500;
    public static final double VALOR_DIA_CARRO = 8000;
    public static final double VALOR_DIA_MOTO = 4000;
    public static final double RECARGO_MOTO = 2000;
    public static final int RESTRICCION_CILINDRAJE_MOTO = 500;
    public static final int LIMITE_HORAS_PARA_DIA = 9;
    
    public static final String ERROR_VEHICULO_YA_EXISTE = "Ya existe un vehiculo con esa placa";
    public static final String ERROR_VEHICULO_NO_EXISTE = "Ese vehiculo no existe";
    
    public static final String STATUS_OK = "OK";
    public static final String STATUS_BAD_REQUEST = "OK";
    public static final String VEHICULO_CREADO = "Vehiculo creado correctamente";
    public static final String VEHICULO_ELIMINADO = "Vehiculo eliminado correctamente";
}
