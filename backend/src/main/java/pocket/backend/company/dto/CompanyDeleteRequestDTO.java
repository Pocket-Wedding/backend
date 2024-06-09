package pocket.backend.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CompanyDeleteRequestDTO {

    @NotBlank
    private String companyName;
}
