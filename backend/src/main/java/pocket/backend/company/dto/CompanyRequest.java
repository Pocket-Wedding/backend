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

    public Company toCompany(CompanyRequest companyRequest) {
        return Company.builder()
                .name(Optional.ofNullable(companyRequest.getName()).orElse(this.name))
                .describe(Optional.ofNullable(companyRequest.getDescribe()).orElse(this.describe))
                .address(Optional.ofNullable(companyRequest.getAddress()).orElse(this.address))
                .phoneNumber(Optional.ofNullable(companyRequest.getPhoneNumber()).orElse(this.phoneNumber))
                .price(Optional.ofNullable(companyRequest.getPrice()).orElse(this.price))
                .imageUrl(Optional.ofNullable(companyRequest.getImageUrl()).orElse(this.imageUrl))
                .build();
    }
}
