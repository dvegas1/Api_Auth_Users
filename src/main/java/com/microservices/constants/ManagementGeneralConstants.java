package com.microservices.constants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("MS_MUTABLE_ARRAY")
public class ManagementGeneralConstants {
    private ManagementGeneralConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String COUNTRY = "INTERNATIONAL";
    public static final String OPERATION_CREATEUSER = "CreateUserV1";
    public static final String FALLA_GENERAL_SISTEMA_MENSAJE = "We are unable to accommodate your request at this time, please try again later";
    public static final String FALLA_GENERAL_SISTEMA_CODE = "-1";

    public static final String USER_CREATE_SUCESS_MESSAGE = "Successful User Create";
    public static final String USER_CREATE_SUCESS_CODE = "00";
    public static final String EMAIL_FOUND_CODE = "-600";
    public static final String EMAIL_FOUND_MESSAGE = "Email already exist";

    public static final String USER_NOT_CREATE_CODE = "-601";
    public static final String USER_NOT_CREATE_MESSAGE = "Could not create user";

    public static final String REQUIRE = " is required";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PHONES= "phone";
    public static final String FIELD_EMAIL= "email";
}
