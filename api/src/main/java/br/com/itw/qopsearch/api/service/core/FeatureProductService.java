/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.service.core;

import br.com.itw.qopsearch.api.persistence.FeatureProductRepository;
import br.com.itw.qopsearch.api.service.IFeatureProductService;
import br.com.itw.qopsearch.domain.ProductFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * CRUD Rest Json 'Controller' for entityFeatureProduct
 * Guick Generate class:
 * https://github.com/wdavilaneto/guick
 * Author: service-wdavilaneto@redhat.com
 */
@Service
class FeatureProductService implements IFeatureProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureProductService.class);

    @Resource(name = "featureProductRepository")
    private FeatureProductRepository featureProductRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductFeature> findAll(Pageable pageable) {
        return featureProductRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductFeature> search(ProductFeature productFeature, Pageable pageable) {
        return featureProductRepository.search(productFeature, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductFeature> searchText(String text, Pageable pageable) {
        return featureProductRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductFeature findOne(Long id) {
        return featureProductRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        featureProductRepository.delete(id);
    }

    @Override
    @Transactional
    public ProductFeature save(ProductFeature productFeature) {
        if (productFeature.getId() != null) {
            featureProductRepository.update(productFeature);
        }
        return featureProductRepository.create(productFeature);
    }


}
