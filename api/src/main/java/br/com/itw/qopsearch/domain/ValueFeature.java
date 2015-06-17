package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Igor on 17/06/2015.
 */
@Entity
@Table(name = "value_feature")
@SequenceGenerator(name = "sq_value_feat", sequenceName = "sq_value_feat")
@Inheritance(strategy= InheritanceType.JOINED)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValueFeature extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_value_feat")
    @Column(name = "id", precision = 32, scale = 0)
    private Long id;

    @NotNull
    @Column(name = "cod_value", nullable = false)
    Integer codValue;

    @NotNull
    @Column(name = "value_option", nullable = false)
    String valueOption;

}
