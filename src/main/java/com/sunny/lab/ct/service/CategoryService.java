package com.sunny.lab.ct.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.lab.ct.client.CTClient;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.category.Category;

import io.vrap.rmf.base.client.ApiHttpResponse;

@Service
public class CategoryService {
    @Autowired
    private CTClient ctClient;

    public CompletableFuture<Category> getCategoryById(String categoryId) {
        ProjectApiRoot apiRoot = ctClient.getInstance();

        return apiRoot.categories()
                .withId(categoryId)
                .get().execute().thenApply(ApiHttpResponse::getBody);
    }

    public CompletableFuture<Category> getCategoryByKey(String key) {
        ProjectApiRoot apiRoot = ctClient.getInstance();
        return apiRoot.categories()
                .withKey(key)
                .get()
                .execute().thenApply(ApiHttpResponse::getBody);
    }

    public void setCtClient(CTClient ctClient) {
        this.ctClient = ctClient;
    }
}
