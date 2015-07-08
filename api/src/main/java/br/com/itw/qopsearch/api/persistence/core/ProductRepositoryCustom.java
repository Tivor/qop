/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.persistence.core;

import br.com.itw.qopsearch.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Product)
     *
     * @param pageable
     * @return
     */
    public Page<Product> findAll(Pageable pageable);

    Page<Product> search(Product entity, Pageable pageable);

    Page<Product> searchText(String text, Pageable pageable);

    Product get(Long id);

    public Product update(Product product);

    public Product create(Product product);

}
