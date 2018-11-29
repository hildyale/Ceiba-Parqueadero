package co.com.ceiba.CeibaParqueadero.Persistencia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, String> {

	
}
