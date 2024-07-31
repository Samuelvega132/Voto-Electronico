package ec.com.webmarket.restful.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.webmarket.restful.domain.Curso_Paralelo;

public interface Curso_ParaleloRepository extends JpaRepository<Curso_Paralelo, Long> {

	List<Curso_Paralelo> findByCurso(String curso);
	
	List<Curso_Paralelo> findByParalelo(String paralelo);

}
