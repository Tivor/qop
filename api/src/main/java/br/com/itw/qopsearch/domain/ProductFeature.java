/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * FeatureProduct
 * A classe FeatureProduct representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela feature_product.
 *${entity.table.comment}
 */
@Entity
@Table(name = "product_feature")
@SequenceGenerator(name = "sq_prod_feat", sequenceName = "sq_prod_feat")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFeature extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_prod_feat")
    @Column(name="id", precision = 32, scale = 0 )
    private Long id;

    @NotNull
    @Column(name = "value", nullable = false)
    String value;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_feature", nullable = false)
    private Feature feature;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;


}