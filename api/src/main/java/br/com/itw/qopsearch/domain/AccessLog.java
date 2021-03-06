package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Igor on 17/06/2015.
 */
@Entity
@Table(name = "access_log")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", precision = 32, scale = 0)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 254)
    @Column(name = "login", nullable = false)
    String login;

    @NotNull
    @Column(name = "operation", nullable = false)
    Integer operation;

    @NotNull
    @Column(name = "params", length = 50000)
    String params;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register", nullable = false)
    private Date register;

}
