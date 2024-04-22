package com.microservices.repository.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class UserPhonesDTO implements Serializable {
    String number;
    String citycode;
    String contrycode;
}
