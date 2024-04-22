package com.microservices.dtos.commons;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.jparams.verifier.tostring.preset.Presets;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;


class ApplicationInfoTest {

    @Test
    void validateConstructor() {
        ApplicationInfo applicationInfo = ApplicationInfo.builder().userHome("A1").hazelcastYmlPath("A7").osArch("A8").javaVendor("A9").build();

        Assertions.assertNotNull(applicationInfo.getUserHome());
        Assertions.assertNotNull(applicationInfo.getHazelcastYmlPath());
        Assertions.assertNotNull(applicationInfo.getOsArch());
        Assertions.assertNotNull(applicationInfo.getJavaVendor());

        EqualsVerifier.simple().forClass(ApplicationInfo.class).verify();
        ToStringVerifier.forClass(ApplicationInfo.class).withPreset(Presets.ECLIPSE).verify();

        Assertions.assertDoesNotThrow((ThrowingSupplier<ApplicationInfo>) ApplicationInfo::new);
    }
}
