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
public class UsersDTO implements Serializable {
    String id;
    String email;
    String name;
    boolean active;
    String created;
    String modified;
    String password;
    String last_login;
}
