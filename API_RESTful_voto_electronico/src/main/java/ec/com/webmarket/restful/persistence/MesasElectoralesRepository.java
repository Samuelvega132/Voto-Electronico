package ec.com.webmarket.restful.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.webmarket.restful.domain.MesasElectorales;

public interface MesasElectoralesRepository extends JpaRepository<MesasElectorales, Long> {

	List<MesasElectorales> findByNombre(String nombre);
	
}
