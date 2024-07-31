package ec.com.webmarket.restful.dto.v1;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class EstudiantesDTO {

    private Long id;
    
    @NotBlank(message = "El nombre no debe estar en blanco")
    private String nombre;
    
    @NotBlank(message = "La cédula no debe estar en blanco")
    private String cedula;
}
