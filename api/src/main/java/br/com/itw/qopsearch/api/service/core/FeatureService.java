/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.com.itw.qopsearch.domain.Feature;
import br.com.itw.qopsearch.api.persistence.FeatureRepository;
import br.com.itw.qopsearch.api.service.IFeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityFeature
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class FeatureService implements IFeatureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureService.class);

    @Resource(name = "featureRepository")
    private FeatureRepository featureRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Feature> findAll(Pageable pageable) {
        return featureRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Feature> search(Feature feature , Pageable pageable) {
        return featureRepository.search(feature, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Feature> searchText(String text , Pageable pageable) {
        return featureRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Feature findOne(Long id) {
        return featureRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        featureRepository.delete(id);
    }

    @Override
    @Transactional
    public Feature save(Feature feature) {
        if (feature.getId() != null){
            featureRepository.update(feature);
        }
        return featureRepository.create(feature);
    }


}
