package com.microservices.repository.entities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "USERS")
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "Skip by JPA")
public class UserEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id", updatable = false, unique = true, nullable = false,length = 32)
    private String id;

    @NotNull
    @NotEmpty
    @Unique
    @Size(max = 100)
    @Column(name = "email", nullable = false, length = 100)
    String email;

    @Size(max = 100)
    @NotNull
    @NotEmpty
    @Column(name = "name", length = 100)
    String name;

    @Builder.Default
    @Column(name = "active")
    Boolean active = true;

    @Column(name = "created",updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified")
    private LocalDateTime modified;

    @CreationTimestamp
    @Column(name = "last_login")
    private LocalDateTime last_login;

    @NotBlank
    @NotEmpty
    @Column(name = "password", length = 200)
    String password;

    public UserEntity(String email, String name,boolean active,String password) {
        this.email = email;
        this.name = name;
        this.active = active;
        this.password = password;
    }

    public UserEntity() {

    }

}
