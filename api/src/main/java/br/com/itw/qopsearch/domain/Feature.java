/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.domain;

import java.io.Serializable;
import javax.persistence.*;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * Feature
 * A classe Feature representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela feature.
 *${entity.table.comment}
 */
@Entity
@Table(name = "feature")
@SequenceGenerator(name = "sq_feature", sequenceName = "sq_feature")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feature extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_feature")
    @Column(name = "id", precision = 32, scale = 0)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    String name;

    @NotNull
    @Size(max = 2000)
    @Column(name = "description", nullable = false, length = 2000)
    String description;

    @NotNull
    @Column(name = "type_measure", nullable = false)
    Long typeMeasure;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @NotNull
    @Column(name = "test_case", nullable = false)
    Integer testCase;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature")
    private List<ProductFeature> productFeatureCollection;

    @ManyToMany
    @JoinTable(
            name="value_group",
            joinColumns={@JoinColumn(name="id_feature", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="id_value", referencedColumnName="id")})
    private List<ValueFeature> optionValues;

}
