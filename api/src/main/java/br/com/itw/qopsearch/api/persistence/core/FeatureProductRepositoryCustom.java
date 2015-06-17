/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.persistence.core;

import br.com.itw.qopsearch.domain.ProductFeature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeatureProductRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (FeatureProduct)
     * @param pageable
     * @return
     */
    public Page<ProductFeature> findAll(Pageable pageable);

    Page<ProductFeature> search(ProductFeature entity, Pageable pageable);

    Page<ProductFeature> searchText(String text, Pageable pageable);

    ProductFeature get(Long id);

    public ProductFeature update(ProductFeature productFeature);

    public ProductFeature create(ProductFeature productFeature);

}
