package pocket.backend.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryUpdateRequest {

    @NotBlank
    String prevName;

    @NotBlank
    String newName;
}
