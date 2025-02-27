package com.relfor.pcs.auditing.util;
import com.devourin.pcs.common.base.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class APIHelper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${pcs_url}")
    private String pcsUrl;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public STenantStore getTenantStore(long tenantId, long storeId) {
        String baseUrl = pcsUrl + "/pcs/v1/tenants/stores/tenantstore/" + tenantId + "/" + storeId;
        try {
            return webClientBuilder.build().get().uri(baseUrl).retrieve().bodyToMono(STenantStore.class).block();
        } catch (Exception e) {
            throw e;
        }
    }
}


