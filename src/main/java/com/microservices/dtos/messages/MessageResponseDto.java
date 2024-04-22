package com.microservices.dtos.messages;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MessageResponseDto {
    String code;

    String responseCode;

    String responseMessage;

    Integer responseHttpCode;

    public static MessageResponseDtoBuilder builder() {
        return new MessageResponseDtoBuilder();
    }

    public static class MessageResponseDtoBuilder {
        private String code;

        private String responseCode;

        private String responseMessage;

        private Integer responseHttpCode;

        public MessageResponseDtoBuilder responseCode(String responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public MessageResponseDtoBuilder responseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public MessageResponseDtoBuilder responseHttpCode(Integer responseHttpCode) {
            this.responseHttpCode = responseHttpCode;
            return this;
        }

        public MessageResponseDto build() {
            return new MessageResponseDto(this.code, this.responseCode, this.responseMessage, this.responseHttpCode);
        }

        public String toString() {
            return "MessageResponseDto.MessageResponseDtoBuilder(code=" + this.code + ", responseCode=" + this.responseCode + ", responseMessage=" + this.responseMessage + ", responseHttpCode=" + this.responseHttpCode + ")";
        }
    }

    public String toString() {
        return "MessageResponseDto(code=" + getCode() + ", responseCode=" + getResponseCode() + ", responseMessage=" + getResponseMessage() + ", responseHttpCode=" + getResponseHttpCode() + ")";
    }
}


