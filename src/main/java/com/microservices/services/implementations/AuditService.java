package com.microservices.services.implementations;

import com.microservices.components.configurations.AsyncConfig;
import com.microservices.constants.ManagementGeneralConstants;
import com.microservices.exceptions.InformationAuthException;
import com.microservices.repository.contracts.AdmconsLogRepository;
import com.microservices.repository.entities.AdmconsLogEntity;
import com.microservices.services.contracts.IAuditService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("ALL")
public class AuditService implements IAuditService {
    private static final Logger LOGGER = LogManager.getLogger(AuditService.class);

    AdmconsLogRepository admconsLogRepository;

    AsyncConfig asyncConfig;

    @Autowired
    public AuditService(AdmconsLogRepository admconsLogRepository, AsyncConfig asyncConfig) {
        this.admconsLogRepository = admconsLogRepository;
        this.asyncConfig = asyncConfig;
    }

    @Override
    @Async("asyncExecutor")
    public void saveAdmconsLogAudit(String email, String client, String action, String rc, String msg) throws InformationAuthException {
        try {
            if (email == null) {
                throw new InformationAuthException("356 V2 email cannot be null");
            }
            var admconsLogAudit = new AdmconsLogEntity();
            admconsLogAudit.setEmail(email);
            admconsLogAudit.setClient(client);
            admconsLogAudit.setAction(action);
            admconsLogAudit.setCountry(ManagementGeneralConstants.COUNTRY);
            admconsLogAudit.setMsg(msg);
            admconsLogAudit.setRc(rc);
            admconsLogRepository.save(admconsLogAudit);
        } catch (Exception e) {
            LOGGER.error("402 General The saveAdmconsLogEntity fail", e);
            throw new InformationAuthException("356 V2 The saveAdmconsLogEntity fail saveCard");
        }
    }

}
