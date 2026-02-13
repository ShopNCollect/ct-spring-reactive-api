package com.sunny.lab.ct;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import com.amit.lab.ct.service.CategoryService;
import com.commercetools.api.models.category.Category;
import com.commercetools.api.models.category.CategoryImpl;

@ExtendWith(SpringExtension.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
    private static final String TEST_CATEGORY_ID = "testcategoryId";
	private static final String TEST_CATEGORY_KEY = "testKey";

    @MockBean
	private CategoryService categoryService;
    
    @Autowired
    private WebTestClient webTestClient;

    @Test
	public void testGetCategoryById() throws Exception {

        Category category = new CategoryImpl();
		category.setId(TEST_CATEGORY_ID);
		when(categoryService.getCategoryById(TEST_CATEGORY_ID)).thenReturn(CompletableFuture.supplyAsync(() -> category));
        
        webTestClient
        .get().uri("/category/id/" + TEST_CATEGORY_ID)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Category.class).value(categoryReturned -> {
            assertThat(categoryReturned.getId()).isEqualTo(TEST_CATEGORY_ID);
        });
	}

    @Test
	public void testGetCategoryByKey() throws Exception {

        Category category = new CategoryImpl();
		category.setKey(TEST_CATEGORY_KEY);
		when(categoryService.getCategoryByKey(TEST_CATEGORY_KEY)).thenReturn(CompletableFuture.supplyAsync(() -> category));
        
        webTestClient
        .get().uri("/category/key/" + TEST_CATEGORY_KEY)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Category.class).value(categoryReturned -> {
            assertThat(categoryReturned.getKey()).isEqualTo(TEST_CATEGORY_KEY);
        });
	}
}
