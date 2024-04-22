package com.microservices.repository.entities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Builder
@Getter
@Setter
@Table(name = "PHONES")
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "Skip by JPA")
public class PhonesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;


    @NotEmpty
    @Column(name = "citycode")
    String citycode;

    @NotNull
    @NotEmpty
    @Column(name = "contrycode")
    String contrycode;

    @NotNull
    @NotEmpty
    @Column(name = "idUser")
    String idUser;

    @NotNull
    @NotEmpty
    @Column(name = "number")
    String number;


    public PhonesEntity(String citycode,String contrycode,String idUser, String number) {
        this.citycode = citycode;
        this.contrycode = contrycode;
        this.idUser = idUser;
        this.number = number;
    }

    public PhonesEntity(int id,String citycode,String contrycode,String idUser, String number) {
        this.id = id;
        this.citycode = citycode;
        this.contrycode = contrycode;
        this.idUser = idUser;
        this.number = number;
    }


    public PhonesEntity() {

    }
}
