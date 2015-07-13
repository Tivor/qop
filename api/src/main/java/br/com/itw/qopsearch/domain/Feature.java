/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * Feature
 * A classe Feature representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela feature.
 * ${entity.table.comment}
 */
@Entity
@Table(name = "feature")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
public class Feature extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "text_measure")
    String textMeasure;

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
            name = "value_group",
            joinColumns = {@JoinColumn(name = "id_feature", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_value", referencedColumnName = "id")})
    private List<ValueFeature> optionValues;

}
