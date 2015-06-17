/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.service;

import java.math.BigDecimal;
import java.util.Date;
import br.com.itw.qopsearch.domain.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityFeature
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
public interface IFeatureService {

    /**
     * Returns an full, but Paged, list of all entities (Feature)
     * @param pageable
     * @return
     */
    public Page<Feature> findAll(Pageable pageable);

    /**
    *
    * @param feature
    * @param pageable
    * @return
    */
    public Page<Feature> search(Feature feature , Pageable pageable);

    /**
    *
    * @param text
    * @param pageable
    * @return
    */
    public Page<Feature> searchText(String text , Pageable pageable);

    /**
     * Return an entity,Feature ,with an Given ID
     * @param id
     * @return
     */
    public Feature findOne(Long id);

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    public void delete(Long id);

    /**
     * Simple save or update an entity
     * @param feature
     * @return
     */
    public Feature save(Feature feature);

}
