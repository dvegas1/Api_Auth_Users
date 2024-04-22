package com.microservices.services.implementations;

import com.microservices.components.configurations.AsyncConfig;
import com.microservices.exceptions.InformationAuthException;
import com.microservices.repository.contracts.AdmconsLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuditServiceTest {
    @MockBean
    AdmconsLogRepository admconsLogRepositoryMock;

    @Mock
    AsyncConfig asyncConfigMock;

    @Mock
    AuditService auditService;

    @Test
    @DisplayName("ADMLOGS AUDIT, EXEPTION INFORMATIONCARDEXEPTION")
    void saveAdmconsLogAudit() throws InformationAuthException {
        auditService = new AuditService(admconsLogRepositoryMock, asyncConfigMock);
        assertThrows(InformationAuthException.class, () -> {
            auditService.saveAdmconsLogAudit(null, null, null, null, null);
        });
    }
}