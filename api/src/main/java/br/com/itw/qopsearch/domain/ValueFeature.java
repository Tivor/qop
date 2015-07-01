package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
public class ValueFeature extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_value_feat")
    @Column(name = "id", precision = 32, scale = 0)
    private Long id;

    @NotNull
    @Column(name = "value_option", nullable = false)
    String valueOption;

}
