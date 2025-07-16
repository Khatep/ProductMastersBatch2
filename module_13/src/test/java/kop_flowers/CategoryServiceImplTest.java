package kop_flowers;

import kz.kop_flowers.model.FlowerMapper;
import kz.kop_flowers.model.dto.CategoryDto;
import kz.kop_flowers.model.entity.Category;
import kz.kop_flowers.model.exception.CategoryNotFoundException;
import kz.kop_flowers.repository.CategoryRepository;
import kz.kop_flowers.service.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private FlowerMapper mapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void getCategoryById_found() {
        Category category = Category.builder().id(1).name("Праздники").build();
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1);
        Assertions.assertEquals("Праздники", result.getName());
        verify(categoryRepository).findById(1);
    }

    @Test
    public void getCategoryById_notFound_throwsException() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class, () ->
                categoryService.getCategoryById(9999));
    }

    @Test
    public void getCategoryDtoById_success() {
        Category category = Category.builder().id(2).name("Ромашки").build();
        CategoryDto dto = CategoryDto.builder().id(2).name("Ромашки").build();

        when(categoryRepository.findById(2)).thenReturn(Optional.of(category));
        when(mapper.fromEntityToDto(category)).thenReturn(dto);

        CategoryDto result = categoryService.getCategoryDtoById(2);
        Assertions.assertEquals("Ромашки", result.getName());
    }

    @Test
    public void getAllCategories_nonEmptyList() {
        List<Category> categories = List.of(
                Category.builder().id(1).name("Розы").build(),
                Category.builder().id(2).name("Пионы").build()
        );
        List<CategoryDto> categoryDtos = List.of(
                CategoryDto.builder().id(1).name("Розы").build(),
                CategoryDto.builder().id(2).name("Пионы").build()
        );

        when(categoryRepository.findAll()).thenReturn(categories);
        when(mapper.fromEntityToDto(categories.get(0))).thenReturn(categoryDtos.get(0));
        when(mapper.fromEntityToDto(categories.get(1))).thenReturn(categoryDtos.get(1));

        List<CategoryDto> result = categoryService.getAllCategories();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Розы", result.get(0).getName());
    }

    @Test
    public void getAllCategories_emptyList() {
        when(categoryRepository.findAll()).thenReturn(List.of());

        List<CategoryDto> result = categoryService.getAllCategories();
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void createCategory_validCategoryDto() {
        CategoryDto dto = CategoryDto.builder().name("НоваяКатегория").build();
        Category categoryToSave = Category.builder().name("НоваяКатегория").build();
        CategoryDto expectedDto = CategoryDto.builder().id(3).name("НоваяКатегория").build();

        when(categoryRepository.save(any())).thenReturn(categoryToSave);
        when(mapper.fromEntityToDto(categoryToSave)).thenReturn(expectedDto);

        CategoryDto result = categoryService.createCategory(dto);

        Assertions.assertEquals(3, result.getId());
        Assertions.assertEquals("НоваяКатегория", result.getName());
        verify(categoryRepository).save(any(Category.class));
    }

}
