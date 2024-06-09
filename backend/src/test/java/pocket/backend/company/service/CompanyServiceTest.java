package pocket.backend.company.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.backend.company.domain.Company;
import pocket.backend.company.domain.CompanyRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest{
    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    private Company company;

    @BeforeEach
    void setup(){
        company = Company.builder()
                .name("test")
                .address("서울시 강남구")
                .phoneNumber("010-1234-5678")
                .build();
    }

}