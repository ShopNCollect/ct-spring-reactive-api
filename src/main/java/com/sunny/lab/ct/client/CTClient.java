package com.sunny.lab.ct.client;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;

import io.vrap.rmf.base.client.oauth2.ClientCredentials;
ß
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class CTClient {
    private ProjectApiRoot projectAPIRoot;

    private ProjectApiRoot createProjectClient() {
        return ApiRootBuilder.of()
                .defaultClient(ClientCredentials.of()
                        .withClientId("<CLIENT_ID>")
                        .withClientSecret("<CLIENT_SECRET>")
                        .build(),
                        ServiceRegion.GCP_US_CENTRAL1)
                .build("<CT_PROJECT>");
    }

    public CTClient() {
        projectAPIRoot = createProjectClient();
    }

    public ProjectApiRoot getInstance() {
        return projectAPIRoot;
    }
}
