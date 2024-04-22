package com.microservices.services.validations;

import com.google.gson.Gson;
import com.microservices.constants.ManagementGeneralConstants;
import com.microservices.exceptions.ValidationErrors;
import com.microservices.repository.dto.UserPhonesDTO;
import com.microservices.repository.entities.PhonesEntity;
import com.microservices.services.implementations.AuthService;
import org.apache.logging.log4j.LogManager;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ValidateCreateUser {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ValidateCreateUser.class);
    public void validPhoneUser(ArrayList<UserPhonesDTO> phones) throws ValidationErrors {

        LOGGER.info("Executin validPhoneUser");

        List<ValidPhoneDto> list = new ArrayList();
        List<ValidPhoneDto> list1 = new ArrayList();
        phones.forEach(item ->{
            if (!validRegx(ManagementGeneralConstants.NUMBER_PHONE_REGEX,item.getNumber())){
                list.add(ValidPhoneDto.builder().field(ManagementGeneralConstants.FIELD_PHONE_NUMBER).valud(item.getNumber()).msgError(ManagementGeneralConstants.NUMBER_PHONE_MESSAGE).build());
            }

            if (!validRegx(ManagementGeneralConstants.CITY_CODE_PHONE_REGEX,item.getNumber())){
                list.add(ValidPhoneDto.builder().field(ManagementGeneralConstants.FIELD_CITY_CODE).valud(item.getNumber()).msgError(ManagementGeneralConstants.CITY_CODE_PHONE_MESSAGE).build());
            }

            if (!validRegx(ManagementGeneralConstants.COUNTRY_CODE_PHONE_REGEX,item.getNumber())){
                list.add(ValidPhoneDto.builder().field(ManagementGeneralConstants.FIELD_CONTRY_CODE).valud(item.getNumber()).msgError(ManagementGeneralConstants.COUNTRY_CODE_PHONE_MESSAGE).build());
            }
        });

        Jsonb jsonb = JsonbBuilder.create();

        if (list.isEmpty()) {
            return;
        }
        throw new ValidationErrors(jsonb.toJson(list));
    }

    private boolean validRegx(String regex,String value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
