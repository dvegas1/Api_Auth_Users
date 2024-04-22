package com.microservices.repository.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

class AdmconsLogEntityTest {

    @Test
    void validateAdmconsLogEntityTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        AdmconsLogEntity admconsLogEntity = new AdmconsLogEntity();
        admconsLogEntity.setIp("1.0.0.1");
        admconsLogEntity.setRc("00");
        admconsLogEntity.setTimestamp(sdf.get2DigitYearStart());
        admconsLogEntity.setCountry("COOP");
        admconsLogEntity.setMsg("SUCESS");
        admconsLogEntity.setClient("NISUM");
        admconsLogEntity.setEmail("darwinvegas1@gmail.com");

        AdmconsLogEntity admconsLogEntity1 = new AdmconsLogEntity();
        admconsLogEntity1.setIp("1.0.0.1");
        admconsLogEntity1.setRc("00");
        admconsLogEntity1.setTimestamp(sdf.get2DigitYearStart());
        admconsLogEntity1.setCountry("COOP");
        admconsLogEntity1.setMsg("SUCESS");
        admconsLogEntity1.setClient("NISUM");
        admconsLogEntity.setEmail("darwinvegas1@gmail.com");
        admconsLogEntity.equals(admconsLogEntity1);
        Assertions.assertTrue(admconsLogEntity.hashCode() > 1);
        Assertions.assertNotNull(admconsLogEntity);
    }
}