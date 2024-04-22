package com.microservices.dtos.commons;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.jparams.verifier.tostring.preset.Presets;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ApplicationSessionTest {

    @Test
    void validateConstructor() {
        ApplicationSession applicationSession = ApplicationSession.builder()
            .userId("user1")
            .requestId("123abc")
            .tenantId("server01abc")
            .build();

        ApplicationSession applicationSession1 = ApplicationSession.builder()
            .userId("user1")
            .requestId("123abc")
            .tenantId("server01abc")
            .build();

        Assertions.assertNotNull(applicationSession.userId);
        Assertions.assertNotNull(applicationSession.requestId);
        Assertions.assertNotNull(applicationSession.tenantId);
        Assertions.assertEquals(applicationSession.toString(), applicationSession1.toString());

        EqualsVerifier.simple().forClass(ApplicationSession.class).verify();
        ToStringVerifier.forClass(ApplicationSession.class)
            .withPreset(Presets.ECLIPSE)
            .verify();

        Assertions.assertNotNull(ApplicationSession.builder().toString());

        Assertions.assertEquals("user1", applicationSession.getUserId());
        Assertions.assertEquals("123abc", applicationSession.getRequestId());
        Assertions.assertEquals("server01abc", applicationSession.getTenantId());

    }

}
