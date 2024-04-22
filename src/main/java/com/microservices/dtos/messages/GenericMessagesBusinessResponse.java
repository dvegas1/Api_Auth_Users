package com.microservices.dtos.messages;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GenericMessagesBusinessResponse {
    private MessageBusinessResponse messageBusinessResponse;

    public GenericMessagesBusinessResponse messageBusinessResponse(MessageBusinessResponse messageBusinessResponse) {
        this.messageBusinessResponse = messageBusinessResponse;
        return this;
    }
}
