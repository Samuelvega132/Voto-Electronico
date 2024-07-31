package ec.com.webmarket.restful.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.webmarket.restful.domain.Estudiantes;

public interface EstudiantesRepository extends JpaRepository<Estudiantes, Long> {

	List<Estudiantes> findByNombre(String nombre);
	
	List<Estudiantes> findByCedula(String cedula);

}
