/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.service;

import br.com.itw.qopsearch.domain.ProductFeature;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *  CRUD Rest Json 'Controller' for entityFeatureProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
public interface IFeatureProductService {

    /**
     * Returns an full, but Paged, list of all entities (FeatureProduct)
     * @param pageable
     * @return
     */
    @Cacheable(value = "featureProductList")
    public Page<ProductFeature> findAll(Pageable pageable);

    /**
    *
    * @param productFeature
    * @param pageable
    * @return
    */
    @Cacheable(value = "featureProductList")
    public Page<ProductFeature> search(ProductFeature productFeature, Pageable pageable);

    /**
    *
    * @param text
    * @param pageable
    * @return
    */
    @Cacheable(value = "featureProductList")
    public Page<ProductFeature> searchText(String text , Pageable pageable);

    /**
     * Return an entity,FeatureProduct ,with an Given ID
     * @param id
     * @return
     */
    public ProductFeature findOne(Long id);

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @CacheEvict(value="featureProductList", allEntries=true)
    public void delete(Long id);

    /**
     * Simple save or update an entity
     * @param productFeature
     * @return
     */
    @CacheEvict(value="featureProductList", allEntries=true)
    public ProductFeature save(ProductFeature productFeature);

}
