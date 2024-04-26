package com.microservices.dtos.base;

import com.microservices.dtos.messages.GenericMessagesBusinessResponse;
import com.microservices.dtos.messages.MessageResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
@Validated
public class ApiBussinesResponse {
    private static final Logger LOGGER = LogManager.getLogger(ApiBussinesResponse.class);

    public ResponseEntity<Object> getResponse(@NotNull String codeMessageResponse) {
        return getResponse(BaseBusinessResponseDto.builder().build(), codeMessageResponse);
    }

    public ResponseEntity<Object> getResponse(BaseBusinessResponseDto baseBusinessResponseDto, @NotNull String codeMessageResponse) {
        String bodyResponse = null;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                MessageResponseDto messageResponseDto = MessageResponseDto.builder().responseMessage(baseBusinessResponseDto.getMessageResponse()).responseCode(baseBusinessResponseDto.messageResponseCode).responseHttpCode(Integer.valueOf(codeMessageResponse)).build();

                baseBusinessResponseDto.dateTransaction = new Date();
                baseBusinessResponseDto.messageResponseCode = messageResponseDto.getResponseCode();
                baseBusinessResponseDto.messageResponse = messageResponseDto.getResponseMessage();
                httpStatus = HttpStatus.valueOf(messageResponseDto.getResponseHttpCode());
                bodyResponse = jsonb.toJson(baseBusinessResponseDto);
            }
            LOGGER.info("Response: {}", bodyResponse);
            return new ResponseEntity<>(bodyResponse, httpStatus);
        } catch (Exception e1) {
            LOGGER.error("Error general parsing object", e1);
            return new ResponseEntity<>(bodyResponse, httpStatus);
        }
    }

    public ResponseEntity<Object> getResponse(GenericMessagesBusinessResponse messagesBusinessResponse, String codeMessageResponse) {
        String bodyResponse = null;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                MessageResponseDto messageResponseDto = MessageResponseDto.builder().responseMessage(codeMessageResponse).responseHttpCode(400).build();

                httpStatus = HttpStatus.valueOf(messageResponseDto.getResponseHttpCode());
                bodyResponse = jsonb.toJson(messagesBusinessResponse);
            }
            LOGGER.debug("Response: {}", bodyResponse);
            return new ResponseEntity<>(bodyResponse, httpStatus);
        } catch (Exception e1) {
            LOGGER.error("Error general parsing object", e1);
            return new ResponseEntity<>(bodyResponse, httpStatus);
        }
    }
}
