package ec.com.webmarket.restful.dto.v1;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Curso_ParaleloDTO {

	private Long id;
	
	@NotBlank(message = "El curso no debe estar en blanco")
    private String curso;
	
	@NotBlank(message = "El paralelo no debe estar en blanco")
    private String paralelo;
}
