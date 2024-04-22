package com.microservices.dtos.base;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbProperty;
import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@SuppressFBWarnings(value = {"EI_EXPOSE_REP2"}, justification = "Skip by constructor")
public class BaseBusinessResponseDto {
    @JsonbProperty("code")
    @JsonProperty("code")
    @Schema(name = "code", description = "Response code")
    String messageResponseCode;

    @JsonbProperty("message")
    @JsonProperty("message")
    @Schema(name = "message", description = "Message detail")
    String messageResponse;

    @JsonbProperty("datetime")
    @JsonProperty("datetime")
    @Schema(name = "datetime", description = "Date transaction")
    @SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "This is value default")
    Date dateTransaction;

    @JsonbProperty("httpcode")
    @JsonProperty("httpcode")
    @Schema(name = "httpcode", description = "Code Http")
    @SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "This is value default")
    Date httpcode;

    protected BaseBusinessResponseDto(BaseBusinessResponseDtoBuilder<?, ?> b) {
        this.messageResponseCode = b.messageResponseCode;
        this.messageResponse = b.messageResponse;
        this.dateTransaction = b.dateTransaction;
    }

    public static BaseBusinessResponseDtoBuilder<?, ?> builder() {
        return new BaseBusinessResponseDtoBuilderImpl();
    }

    private static final class BaseBusinessResponseDtoBuilderImpl extends BaseBusinessResponseDtoBuilder<BaseBusinessResponseDto, BaseBusinessResponseDtoBuilderImpl> {
        private BaseBusinessResponseDtoBuilderImpl() {}

        protected BaseBusinessResponseDtoBuilderImpl self() {
            return this;
        }

        public BaseBusinessResponseDto build() {
            return new BaseBusinessResponseDto(this);
        }
    }

    public static abstract class BaseBusinessResponseDtoBuilder<C extends BaseBusinessResponseDto, B extends BaseBusinessResponseDtoBuilder<C, B>> {
        private String messageResponseCode;

        private String messageResponse;

        private Date dateTransaction;

        protected abstract B self();

        public abstract C build();

        @JsonProperty("code")
        public B messageResponseCode(String messageResponseCode) {
            this.messageResponseCode = messageResponseCode;
            return self();
        }

        @JsonProperty("message")
        public B messageResponse(String messageResponse) {
            this.messageResponse = messageResponse;
            return self();
        }

        @JsonProperty("datetime")
        public B dateTransaction(Date dateTransaction) {
            this.dateTransaction = dateTransaction;
            return self();
        }

        public String toString() {
            return "BaseBusinessResponseDto.BaseBusinessResponseDtoBuilder(messageResponseCode=" + this.messageResponseCode + ", messageResponse=" + this.messageResponse + ", dateTransaction=" + this.dateTransaction + ")";
        }
    }

    public BaseBusinessResponseDto(String messageResponseCode, String messageResponse, Date dateTransaction) {
        this.messageResponseCode = messageResponseCode;
        this.messageResponse = messageResponse;
        this.dateTransaction = dateTransaction;
    }
}

