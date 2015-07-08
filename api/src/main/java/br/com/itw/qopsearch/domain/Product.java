/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;


/**
 * Product
 * A classe Product representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela product.
 * ${entity.table.comment}
 */
@Entity
@Table(name = "product")
@SequenceGenerator(name = "sq_product", sequenceName = "sq_product")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
public class Product extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_product")
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
    @Size(max = 50)
    @Column(name = "image", nullable = false, length = 50)
    String image;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2, length = 0)
    BigDecimal price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @NotNull
    @Column(name = "test_case", nullable = false)
    Integer testCase;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductFeature> productFeatureCollection;


}
