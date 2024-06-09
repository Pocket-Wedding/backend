package pocket.backend.location.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LocationDeleteRequest {

    @NotBlank
    String name;
}
