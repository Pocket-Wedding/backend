package pocket.backend.category.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.backend.category.domain.Category;
import pocket.backend.category.domain.CategoryRepository;
import pocket.backend.category.dto.CategoryRegisterRequest;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryRepository);
        category = Category.builder()
                .name("test")
                .build();
    }

    @DisplayName("카테고리를 등록한다.")
    @Test
    void registerCategory() {
        CategoryRegisterRequest categoryRegisterRequest = new CategoryRegisterRequest("test");

        Category category = Category.builder()
                .name("test")
                .build();

        when(categoryRepository.existsByName(categoryRegisterRequest.getName())).thenReturn(false);
        when(categoryRepository.save(any())).thenReturn(category);

        assertThat(categoryService.registerCategory(categoryRegisterRequest)).isEqualTo(Optional.of(category));

    }

    @DisplayName("카테고리 중복 등록시 예외 발생")
    @Test
    void registerCategoryDuplicated() {
        CategoryRegisterRequest categoryRegisterRequest = new CategoryRegisterRequest("test");

        when(categoryRepository.existsByName("test")).thenReturn(true);

        assertThatThrownBy(() -> categoryService.registerCategory(categoryRegisterRequest))
                .isInstanceOf(DuplicatedException.class)
                .hasMessage(ErrorCode.DUPLICATED_CATEGORY_NAME.getMessage());
    }

    @DisplayName("존재하지 않는 카테고리 조회시 예외 발생")
    @Test
    void countCategory() {
        Category category = Category.builder()
                .name("test")
                .build();
        assertThatThrownBy(() -> categoryService.countCategory("test"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ErrorCode.NOT_FOUND_CATEGORY.getMessage());
    }

    @DisplayName("존재하는 카테고리 조회시 카테고리의 업체 개수 반환")
    @Test
    void countCategoryExist() {
        Category category = Category.builder()
                .name("test")
                .build();
        when(categoryRepository.findByName("test")).thenReturn(Optional.of(category));
        assertThat(categoryService.countCategory("test")).isEqualTo(0L);
    }
}