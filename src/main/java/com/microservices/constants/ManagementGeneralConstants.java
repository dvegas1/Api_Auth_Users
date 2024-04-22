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

    public static final String NUMBER_PHONE_REGEX = "^\\d{1,16}$";
    public static final String NUMBER_PHONE_MESSAGE = "The field number must contain only numeris and a minimum length of 7 and a maximum length of 16";

    public static final String CITY_CODE_PHONE_REGEX = "^\\d{1,2}$";
    public static final String CITY_CODE_PHONE_MESSAGE = "The field citycode must contain only numeris and a minimum length of 1 and a maximum length of 2";

    public static final String COUNTRY_CODE_PHONE_REGEX = "^\\d{1,2}$";
    public static final String COUNTRY_CODE_PHONE_MESSAGE = "The field contrycode must contain only numeris and a minimum length of 1 and a maximum length of 2";

    public static final String FIELD_CITY_CODE = "name";
    public static final String FIELD_PHONE_NUMBER= "number";
    public static final String FIELD_CONTRY_CODE= "email";
}
