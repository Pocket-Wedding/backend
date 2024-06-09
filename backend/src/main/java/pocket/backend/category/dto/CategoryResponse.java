package pocket.backend.category.dto;

import lombok.Getter;
import pocket.backend.category.domain.Category;

import java.util.List;

@Getter
public class CategoryResponse {
    String name;
    Long totalCount;

    public CategoryResponse(String name, Long totalCount) {
        this.name = name;
        this.totalCount = totalCount;
    }

    public static List<CategoryResponse> toList(List<Category> categoryList){
        return categoryList.stream()
                .map(CategoryResponse::of)
                .toList();
    }
    private static CategoryResponse of(Category category){
        return new CategoryResponse(
                category.getName(),
                category.getTotalCount()
        );
    }
}
