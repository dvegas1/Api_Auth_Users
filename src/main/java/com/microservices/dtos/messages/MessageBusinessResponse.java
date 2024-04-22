package com.microservices.dtos.messages;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class MessageBusinessResponse {
    private List<String> messagesBusiness;

    public MessageBusinessResponse(List<String> messagesBusiness) {
        this.messagesBusiness = messagesBusiness;
    }

    public List<String> getMessagesBusiness() {
        return messagesBusiness;
    }

    public void setMessagesBusiness(List<String> messagesBusiness) {
        this.messagesBusiness = messagesBusiness;
    }
}
