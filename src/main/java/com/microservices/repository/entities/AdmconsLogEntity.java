package com.microservices.repository.entities;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "ADMCONS_LOG")
@SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "Skip by JPA")
public class AdmconsLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuenciaAdmconsLog")
    @SequenceGenerator(name = "secuenciaAdmconsLog", sequenceName = "SEQ_LOG", allocationSize = 1)
    @Column(name = "LOG_ID")
    private long logId;
    @Basic
    @Column(name = "EMAIL", length = 30)
    private String email;
    @Basic
    @Column(name = "COUNTRY", length = 20)
    private String country;
    @Basic
    @Column(name = "CLIENT", length = 32)
    private String client;
    @Basic
    @Column(name = "IP", length = 64)
    private String ip;
    @Basic
    @Column(name = "RC", length = 4)
    private String rc;
    @Basic
    @Column(name = "MSG", length = 100)
    private String msg;
    @Basic
    @Column(name = "ACTION", length = 20)
    private String action;
    @Basic
    @Column(name = "TIME_STAMP")
    private Date timestamp = new Timestamp(new Date().getTime());
}
