package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Igor on 17/06/2015.
 */
@Entity
@Table(name = "access_log")
@SequenceGenerator(name = "sq_log", sequenceName = "sq_log")
@Inheritance(strategy= InheritanceType.JOINED)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_log")
    @Column(name = "id", precision = 32, scale = 0)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "login", nullable = false, length = 50)
    String login;

    @NotNull
    @Column(name = "operation", nullable = false)
    Integer operation;

    @NotNull
    @Column(name = "params")
    String params;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register", nullable = false)
    private Date register;

}
