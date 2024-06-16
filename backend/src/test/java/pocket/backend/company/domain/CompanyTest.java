package pocket.backend.company.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pocket.backend.category.domain.Category;
import pocket.backend.company.dto.CompanyUpdateRequestDTO;
import pocket.backend.location.domain.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CompanyTest {

    private Category category;
    private Location location;
    private Company company;

    @BeforeEach
    public void setUp() {
        category = new Category("스튜디오");
        location = new Location("서울");
        company = Company.builder()
                .name("Test Company")
                .describe("This is a test company.")
                .address("123 Test Street")
                .phoneNumber("010-1234-5678")
                .price(100000)
                .imageUrl("http://example.com/image.jpg")
                .category(category)
                .location(location)
                .build();
    }

    @Test
    public void testCompanyCreation() {
        assertNotNull(company);
        assertEquals("Test Company", company.getName());
        assertEquals("This is a test company.", company.getDescribe());
        assertEquals("123 Test Street", company.getAddress());
        assertEquals("010-1234-5678", company.getPhoneNumber());
        assertEquals(100000, company.getPrice());
        assertEquals("http://example.com/image.jpg", company.getImageUrl());
        assertEquals(category, company.getCategory());
        assertEquals(location, company.getLocation());
    }

    @Test
    public void testUpdateCompany() {
        CompanyUpdateRequestDTO updateDTO = new CompanyUpdateRequestDTO(
                1L,
                "Updated Company",
                "This is an updated description.",
                "456 Updated Street",
                "010-9876-5432",
                200000,
                "http://example.com/updated_image.jpg"
        );

        company.update(updateDTO);

        assertEquals("Updated Company", company.getName());
        assertEquals("This is an updated description.", company.getDescribe());
        assertEquals("456 Updated Street", company.getAddress());
        assertEquals("010-9876-5432", company.getPhoneNumber());
        assertEquals(200000, company.getPrice());
        assertEquals("http://example.com/updated_image.jpg", company.getImageUrl());
    }

    @Test
    public void testUpdateCompanyWithNullValues() {
        CompanyUpdateRequestDTO updateDTO = new CompanyUpdateRequestDTO();

        company.update(updateDTO);

        assertEquals("Test Company", company.getName());
        assertEquals("This is a test company.", company.getDescribe());
        assertEquals("123 Test Street", company.getAddress());
        assertEquals("010-1234-5678", company.getPhoneNumber());
        assertEquals(100000, company.getPrice());
        assertEquals("http://example.com/image.jpg", company.getImageUrl());
    }

    @Test
    public void testUpdateCompanyWithPartialValues() {
        CompanyUpdateRequestDTO updateDTO = new CompanyUpdateRequestDTO(
                1L,
                "Partially Updated Company",
                "TEST!",
                "서울시 종로구",
                "010-5678-1234",
                100000,
                "http://example.com/image.jpg"
        );

        company.update(updateDTO);

        assertEquals("Partially Updated Company", company.getName());
        assertEquals("TEST!", company.getDescribe());
        assertEquals("서울시 종로구", company.getAddress());
        assertEquals("010-5678-1234", company.getPhoneNumber());
        assertEquals(100000, company.getPrice());
        assertEquals("http://example.com/image.jpg", company.getImageUrl());
    }

    @Test
    public void testCategoryAssignment() {
        Category newCategory = new Category("Healthcare");
        company = Company.builder()
                .name("Test Company")
                .describe("This is a test company.")
                .address("123 Test Street")
                .phoneNumber("010-1234-5678")
                .price(100000)
                .imageUrl("http://example.com/image.jpg")
                .category(newCategory)
                .location(location)
                .build();

        assertEquals(newCategory, company.getCategory());
    }

    @Test
    public void testLocationAssignment() {
        Location newLocation = new Location("Busan");
        company = Company.builder()
                .name("Test Company")
                .describe("This is a test company.")
                .address("123 Test Street")
                .phoneNumber("010-1234-5678")
                .price(100000)
                .imageUrl("http://example.com/image.jpg")
                .category(category)
                .location(newLocation)
                .build();

        assertEquals(newLocation, company.getLocation());
    }
}