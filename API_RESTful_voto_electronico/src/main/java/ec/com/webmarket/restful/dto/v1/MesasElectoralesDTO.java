package ec.com.webmarket.restful.dto.v1;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MesasElectoralesDTO {

	private Long id;
	
	@NotBlank(message = "El nombre no debe estar en blanco")
    private String nombre;
}
