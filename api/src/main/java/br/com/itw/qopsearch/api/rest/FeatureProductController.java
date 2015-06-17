/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.rest;

import br.com.itw.commons.rest.dto.Pagination;
import br.com.itw.commons.rest.dto.SearchFilter;
import br.com.itw.qopsearch.domain.ProductFeature;
import br.com.itw.qopsearch.api.service.IFeatureProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityFeatureProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/FeatureProduct")
public class FeatureProductController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureProductController.class);

    @Resource(name = "featureProductService")
    private IFeatureProductService featureProductService;

    /**
     * Returns an full, but Paged, list of all entities (FeatureProduct)
     * @param pagination
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Page<ProductFeature>> findAll(Pagination pagination) {
        return new HttpEntity(featureProductService.findAll(pagination.getPageable()));
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public HttpEntity<Page<ProductFeature>> search(@RequestBody SearchFilter<ProductFeature> filter) {
        return new ResponseEntity(featureProductService.search(filter.getContent() , filter.getPageable()), HttpStatus.OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.GET)
    public HttpEntity<Page<ProductFeature>> searchTextGet(String text) {
        return new HttpEntity(featureProductService.searchText(text , DEFAULT_PAGE ));
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<ProductFeature>> searchText(@RequestBody SearchFilter<String> filter) {
        return new HttpEntity(featureProductService.searchText(filter.getContent() , filter.getPageable()));
    }
    /**
     * Return an entity,FeatureProduct ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<ProductFeature> get(@PathVariable Long id) {
        return new HttpEntity<>(featureProductService.findOne(id));
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        featureProductService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param productFeature
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<ProductFeature> save(@RequestBody ProductFeature productFeature) {
        featureProductService.save(productFeature);
        return new HttpEntity<ProductFeature>(productFeature);
    }

}
