package pocket.backend.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pocket.backend.company.domain.Company;

import java.util.Optional;

@AllArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@Getter
public class CompanyRequest {

    @NotBlank
    private String name;

    private String describe;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private Integer price;

    private String imageUrl;

    @NotBlank
    private String categoryName;

    @NotBlank
    private String locationName;
}
