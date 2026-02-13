package com.sunny.lab.ct.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.lab.ct.client.CTClient;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.category.Category;

import io.vrap.rmf.base.client.ApiHttpResponse;

@Service
public class CustomerService {
    @Autowired
    private CTClient ctClient;

    public CompletableFuture<Customer> getCustomerById(String customerId) {
        ProjectApiRoot apiRoot = ctClient.getInstance();

        return apiRoot.customers()
                .withId(customerId)
                .get().execute().thenApply(ApiHttpResponse::getBody);
    }

    public CompletableFuture<Customer> getCustomerByEmail(String email) {
        ProjectApiRoot apiRoot = ctClient.getInstance();
        // Search for Customers whose email address matches the predicate variable
        CustomerPagedQueryResponse customerToFind = apiRoot
                        .customers()
                        .get()
                        .withWhere("email = :customerEmail", "customerEmail", email)
                        .executeBlocking()
                        .getBody();

                    // As email addresses must be unique, this returns either 0 or 1 Customers.
                    // If 0, then no Customer exists with this email address.
                    if (customerToFind.getCount() == 0) {
                        System.out.println("This email address is not registered.");
                    } else {
                        // Since there can be only one Customer resource in the result, it must be the first entry of the results array. This outputs the Customer's id.
                        String customerID = customerToFind.getResults().get(0).getId();
                        System.out.println(customerID);
                    }
        return customerToFind.getResults().get(0).thenApply(ApiHttpResponse::getBody);
                
    }

    public void setCtClient(CTClient ctClient) {
        this.ctClient = ctClient;
    }
}
