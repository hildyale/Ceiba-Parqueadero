package co.com.ceiba.CeibaParqueadero.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Vigilante;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

@RestController
@RequestMapping("/vehiculos")
public class VigilanteController {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	Vigilante vigilante;

	@GetMapping()
	public Object obtenerTodos(){
		try {
			return vehiculoRepository.obtenerTodosLosVehiculos();
		} catch (Exception e) {
			Respuesta respuesta = new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage());
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Respuesta> registrarVehiculo(@Valid @RequestBody Vehiculo vehiculo){
		try {
			vigilante.registrarVehiculo(vehiculo);
			Respuesta respuesta = new Respuesta(Constants.STATUS_OK,Constants.VEHICULO_CREADO);
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.OK);
		}catch(ParqueaderoException e) {
			e.printStackTrace();
			Respuesta respuesta = new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage());
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/{placa}")
	public Object obtenerVehiculoPorPlaca(@PathVariable(value="placa") String placa) throws ParqueaderoException {
		try {
			return new ResponseEntity<Vehiculo>(vehiculoRepository.obtenerVehiculoPorPlaca(placa),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Respuesta>(new Respuesta("BAD REQUEST",e.getMessage()),HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
	@DeleteMapping("/{placa}")
	public ResponseEntity<Respuesta> eliminarVehiculo(@PathVariable(value="placa") String placa) throws ParqueaderoException{
		try {
			vehiculoRepository.eliminarVehiculo(placa);
			Respuesta respuesta = new Respuesta(Constants.STATUS_OK,Constants.VEHICULO_ELIMINADO);
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.OK);
		}catch(Exception e) {
			Respuesta respuesta = new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage());
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/cantidad")
	public ResponseEntity<Respuesta> obetenerCantidadVehiculos(){
		try {
			Respuesta respuesta = new Respuesta(Constants.STATUS_OK,vehiculoRepository.obtenerCantidadVehiculos().toString());
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.OK);
		}catch(Exception e) {
			Respuesta respuesta = new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage());
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.BAD_REQUEST);
		}
	}
	

}
