package com.microservices.services.validations;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ValidPhoneDto {
    String field = "";
    String valud = "";
    String msgError = "";
}
