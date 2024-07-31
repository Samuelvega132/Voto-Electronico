package ec.com.webmarket.restful.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.webmarket.restful.domain.Candidatos;

public interface CandidatosRepository extends JpaRepository<Candidatos, Long> {

	List<Candidatos> findByNombre(String nombre);
	
	List<Candidatos> findByPartido(String partido);

}
