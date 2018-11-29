package co.com.ceiba.CeibaParqueadero.Controller;

public class Respuesta {

	private String status;
	private String message;
	
	public Respuesta() {
		
	}
	
	public Respuesta(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
