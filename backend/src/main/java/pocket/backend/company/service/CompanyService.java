package pocket.backend.company.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pocket.backend.category.domain.Category;
import pocket.backend.category.service.CategoryService;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;
import pocket.backend.company.domain.Company;
import pocket.backend.company.domain.CompanyRepository;
import pocket.backend.company.dto.CategoryRequestDTO;
import pocket.backend.company.dto.CompanyRequest;
import pocket.backend.company.dto.LocationRequestDTO;
import pocket.backend.company.dto.TotalRequestDTO;
import pocket.backend.location.domain.Location;
import pocket.backend.location.service.LocationService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CategoryService categoryService;
    private final LocationService locationService;


    // 모든 업체를 조사하는 메서드
    public Optional<List<Company>> findAllCompanies(){
        return Optional.of(companyRepository.findAll());
    }

    // 특정 지역에 있는 모든 업체 조회
    public Optional<List<Company>> findAllCompaniesByLocationId(LocationRequestDTO locationRequestDTO){
        Long locationId = locationService.getLocationIdByName(locationRequestDTO.getLocationName());
        return companyRepository.findAllByLocationId(locationId);
    }

    // 특정 카테고리에 있는 모든 업체 조회
    public Optional<List<Company>> findAllCompaniesByCategoryId(CategoryRequestDTO categoryRequestDTO){
        Long categoryId = categoryService.getCategoryIdByName(categoryRequestDTO.getCategoryName());
        return companyRepository.findAllByCategoryId(categoryId);
    }

    // 특정 지역에 있는 스튜디오 업체 조회
    public Optional<List<Company>> findAllCompaniesByLocationIdAndCategoryId(TotalRequestDTO totalRequestDTO) {
        Long locationId = locationService.getLocationIdByName(totalRequestDTO.getLocationName());
        Long categoryId = categoryService.getCategoryIdByName(totalRequestDTO.getCategoryName());
        return companyRepository.findAllByLocationIdAndCategoryId(locationId, categoryId);
    }

    // 업체를 등록하는 메서드
    @Transactional
    public void registerCompany(CompanyRequest companyRequest) {
        Location location = locationService.getLocationByName(companyRequest.getLocationName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );
        Category category = categoryService.getCategoryByName(companyRequest.getCategoryName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        Company registeredCompany = Company.builder()
                        .name(companyRequest.getName())
                        .describe(companyRequest.getDescribe())
                        .address(companyRequest.getAddress())
                        .phoneNumber(companyRequest.getPhoneNumber())
                        .price(companyRequest.getPrice())
                        .imageUrl(companyRequest.getImageUrl())
                        .category(category)
                        .location(location)
                        .build();
        companyRepository.save(registeredCompany);
    }

}
