package co.com.ceiba.CeibaParqueadero.Controller;

import java.util.LinkedList;
import java.util.List;

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
import co.com.ceiba.CeibaParqueadero.Persistencia.Builder.VehiculoBuilder;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

@RestController
@RequestMapping("/vehiculos")
public class VigilanteController {
	
	@Autowired
	Vigilante vigilante;

	@GetMapping()
	public Object consultaVehiculos(){
		try {
			LinkedList<VehiculoRest> vehiculosRest = new LinkedList<>();
			List<Vehiculo> vehiculos = vigilante.obtenerTodosLosVehiculos();
			for(Vehiculo vehiculo: vehiculos){
				vehiculosRest.add(new VehiculoRest(vehiculo.getPlaca(),vehiculo.getTipo(),vehiculo.getFechaIngreso()));
			}
			return vehiculosRest;
			
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
	

	@DeleteMapping("/{placa}")
	public ResponseEntity<Respuesta> salidaVehiculo(@PathVariable(value="placa") String placa) throws ParqueaderoException{
		try {
			Respuesta respuesta = new Respuesta(Constants.STATUS_OK,String.valueOf(vigilante.salidaVehiculo(placa)));
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			Respuesta respuesta = new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage());
			return new ResponseEntity<Respuesta>(respuesta,HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
