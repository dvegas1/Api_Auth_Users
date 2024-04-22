package com.microservices.services.contracts;

import com.microservices.exceptions.InformationAuthException;

public interface IAuditService {
    void saveAdmconsLogAudit(String email, String client, String action,String rc,String msg) throws InformationAuthException;
}
