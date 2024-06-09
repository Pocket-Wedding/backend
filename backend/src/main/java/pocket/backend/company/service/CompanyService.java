package pocket.backend.company.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pocket.backend.category.domain.Category;
import pocket.backend.category.service.CategoryService;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;
import pocket.backend.company.domain.Company;
import pocket.backend.company.domain.CompanyRepository;
import pocket.backend.company.dto.*;
import pocket.backend.location.domain.Location;
import pocket.backend.location.service.LocationService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final CategoryService categoryService;
    private final LocationService locationService;

    // 모든 업체를 조사하는 메서드
    @Transactional
    public List<CompanyResponse> findAllCompanies(){
        return Collections.unmodifiableList(CompanyResponse.listOf(companyRepository.findAll()));
    }

    // 특정 지역에 있는 모든 업체 조회
    @Transactional
    public List<CompanyResponse> findAllCompaniesByLocationId(String name){
        Long locationId = locationService.getLocationIdByName(name);
        return CompanyResponse.listOf(companyRepository.findAllByLocationId(locationId).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_COMPANY)
        ));
    }

    // 특정 카테고리에 있는 모든 업체 조회
    @Transactional
    public List<CompanyResponse> findAllCompaniesByCategoryId(String categoryName){
        Long categoryId = categoryService.getCategoryIdByName(categoryName);
        return CompanyResponse.listOf(companyRepository.findAllByCategoryId(categoryId).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_COMPANY)
        ));
    }

    // 특정 지역에 있는 스튜디오 업체 조회
    @Transactional
    public List<CompanyResponse> findAllCompaniesByLocationIdAndCategoryId(String locationName, String categoryName) {
        Long locationId = locationService.getLocationIdByName(locationName);
        Long categoryId = categoryService.getCategoryIdByName(categoryName);
        return CompanyResponse.listOf(companyRepository.findAllByLocationIdAndCategoryId(locationId, categoryId).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_COMPANY)
        ));
    }

    // 업체를 등록하는 메서드
    @Transactional
    public Optional<Void> registerCompany(CompanyRegisterRequestDTO companyRegisterRequestDTO) {
        Location location = locationService.getLocationByName(companyRegisterRequestDTO.getLocationName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );

        location.increaseTotalCount();

        Category category = categoryService.getCategoryByName(companyRegisterRequestDTO.getCategoryName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );

        category.increaseTotalCount();

        Company registeredCompany = Company.builder()
                        .name(companyRegisterRequestDTO.getName())
                        .describe(companyRegisterRequestDTO.getDescribe())
                        .address(companyRegisterRequestDTO.getAddress())
                        .phoneNumber(companyRegisterRequestDTO.getPhoneNumber())
                        .price(companyRegisterRequestDTO.getPrice())
                        .imageUrl(companyRegisterRequestDTO.getImageUrl())
                        .category(category)
                        .location(location)
                        .build();

        companyRepository.save(registeredCompany);
        return Optional.empty();
    }

    @Transactional
    public Optional<Void> updateCompany(CompanyUpdateRequestDTO companyUpdateRequestDTO) {
        Company company = companyRepository.findById(companyUpdateRequestDTO.getId()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_COMPANY)
        );

        company.update(companyUpdateRequestDTO);
        return Optional.empty();
    }

    @Transactional
    public Optional<Void> deleteCompany(CompanyDeleteRequestDTO companyDeleteRequestDTO) {
        Company company = companyRepository.findByName(companyDeleteRequestDTO.getCompanyName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_COMPANY)
        );

        Location location = company.getLocation();
        location.decreaseTotalCount();

        Category category = company.getCategory();
        category.decreaseTotalCount();


        companyRepository.delete(company);

        return Optional.empty();
    }
}
