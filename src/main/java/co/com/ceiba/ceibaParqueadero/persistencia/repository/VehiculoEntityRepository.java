package co.com.ceiba.ceibaParqueadero.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.ceibaParqueadero.persistencia.entity.VehiculoEntity;

@Repository
public interface VehiculoEntityRepository extends JpaRepository<VehiculoEntity, String> {

	Long countByTipo(String tipo);
	List<VehiculoEntity> findAllByOrderByFechaIngresoDesc();
	
}
