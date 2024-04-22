package com.microservices.components.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApiCommonsHelperTest {

    @Test
     void getClientHttpRequestFactory() {
        var getClient = ApiCommonsHelper.getClientHttpRequestFactory();
        Assertions.assertNotNull(getClient);

    }

    @Test
    void getDefaultHeader() {
        var getDefauldHeader =  ApiCommonsHelper.getDefaultHeader();
        Assertions.assertNotNull(getDefauldHeader);
    }


}