package pocket.backend.hall.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HallRequestDTO {

    @NotBlank
    private String hallName;

}
