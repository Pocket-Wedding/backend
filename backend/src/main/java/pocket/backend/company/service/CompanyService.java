package pocket.backend.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pocket.backend.company.domain.Company;
import pocket.backend.company.domain.CompanyRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@Service
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    // 모든 업체를 조사하는 메서드
    public Optional<List<Company>> findAllCompanies(){
        return Optional.of(companyRepository.findAll());
    }

    // 스튜디오 업체만 조사하는 메서드
    public Optional<List<Company>> findAllStudios(){
        return companyRepository.findAllByCategoryId(1L);
    }
    // 드레스 업체만 조사하는 메서드
    public Optional<List<Company>> findAllDresses(){
        return companyRepository.findAllByCategoryId(2L);
    }
    // 메이크업 업체만 조사하는 메서드
    public Optional<List<Company>> findAllMakeups(){
        return companyRepository.findAllByCategoryId(3L);
    }

    // 특정 지역에 있는 모든 업체 조회
    public Optional<List<Company>> findAllCompaniesByLocationId(Long locationId){
        return companyRepository.findAllByLocationId(locationId);
    }

    // 특정 지역에 있는 스튜디오 업체 조회
    public Optional<List<Company>> findAllCompaniesByLocationIdAndCategoryId(Long locationId, Long categoryId) {
        return companyRepository.findAllByLocationIdAndCategoryId(locationId, categoryId);
    }
}
