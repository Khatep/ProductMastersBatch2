package kz.kop_flowers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.kop_flowers.controller.CategoryController;
import kz.kop_flowers.model.dto.CategoryDto;
import kz.kop_flowers.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
@ContextConfiguration(classes = {
        CategoryController.class,
        CategoryControllerTest.TestConfig.class
})
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CategoryService categoryService() {
            return Mockito.mock(CategoryService.class);
        }
    }

    @Test
    void testGetAllCategories() throws Exception {
        List<CategoryDto> categories = List.of(
                CategoryDto.builder().id(1).name("Подарки").build(),
                CategoryDto.builder().id(2).name("Свадьба").build()
        );

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Подарки"))
                .andExpect(jsonPath("$[1].name").value("Свадьба"));

        verify(categoryService).getAllCategories();
    }

    @Test
    void testCreateCategory() throws Exception {
        CategoryDto requestDto = CategoryDto.builder().name("8 марта").build();
        CategoryDto responseDto = CategoryDto.builder().id(3).name("8 марта").build();

        when(categoryService.createCategory(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("8 марта"));

        verify(categoryService).createCategory(requestDto);
    }
}
