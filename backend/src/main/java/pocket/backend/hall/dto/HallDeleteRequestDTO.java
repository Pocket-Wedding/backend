package pocket.backend.hall.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HallDeleteRequestDTO {

    @NotBlank
    private String hallName;

}
