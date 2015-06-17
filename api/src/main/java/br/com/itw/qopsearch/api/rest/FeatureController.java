/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.rest;

import java.math.BigDecimal;
import java.util.Date;
import br.com.itw.commons.rest.dto.Pagination;
import br.com.itw.commons.rest.dto.SearchFilter;
import br.com.itw.qopsearch.domain.Feature;
import br.com.itw.qopsearch.api.service.IFeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *  CRUD Rest Json 'Controller' for entityFeature
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/Feature")
public class FeatureController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureController.class);

    @Resource(name = "featureService")
    private IFeatureService featureService;

    /**
     * Returns an full, but Paged, list of all entities (Feature)
     * @param pagination
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Page<Feature>> findAll(Pagination pagination) {
        return new HttpEntity(featureService.findAll(pagination.getPageable()));
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public HttpEntity<Page<Feature>> search(@RequestBody SearchFilter<Feature> filter) {
        return new ResponseEntity(featureService.search(filter.getContent() , filter.getPageable()), HttpStatus.OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.GET)
    public HttpEntity<Page<Feature>> searchTextGet(String text) {
        return new HttpEntity(featureService.searchText(text , DEFAULT_PAGE ));
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<Feature>> searchText(@RequestBody SearchFilter<String> filter) {
        return new HttpEntity(featureService.searchText(filter.getContent() , filter.getPageable()));
    }
    /**
     * Return an entity,Feature ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Feature> get(@PathVariable Long id) {
        return new HttpEntity<>(featureService.findOne(id));
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        featureService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param feature
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Feature> save(@RequestBody Feature feature) {
        featureService.save(feature);
        return new HttpEntity<Feature>(feature);
    }

}
