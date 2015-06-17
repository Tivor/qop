/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import br.com.itw.commons.rest.dto.Pagination;
import br.com.itw.commons.rest.dto.SearchFilter;
import br.com.itw.qopsearch.domain.Product;
import br.com.itw.qopsearch.api.service.IProductService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 *  CRUD Rest Json 'Controller' for entityProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/Product")
public class ProductController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0,20);

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Resource(name = "productService")
    private IProductService productService;

    /**
     * Returns an full, but Paged, list of all entities (Product)
     * @param pagination
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Page<Product>> findAll(Pagination pagination) {
        return new HttpEntity(productService.findAll(pagination.getPageable()));
    }

    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public HttpEntity<Page<Product>> search(@RequestBody SearchFilter<Product> filter) {
        return new ResponseEntity(productService.search(filter.getContent() , filter.getPageable()), HttpStatus.OK);
    }

    /**
     * Request first page of a text based search on all fields ignoring associations
     * @param text
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.GET)
    public HttpEntity<Page<Product>> searchTextGet(String text) {
        return new HttpEntity(productService.searchText(text , DEFAULT_PAGE ));
    }
    /**
     * Returns a paged and filtered list with an given example (ignoring relationship examples beyond id)
     * @param filter
     * @return
     */
    @RequestMapping(value = "/searchText", method = RequestMethod.POST)
    public HttpEntity<Page<Product>> searchText(@RequestBody SearchFilter<String> filter) {
        return new HttpEntity(productService.searchText(filter.getContent() , filter.getPageable()));
    }
    /**
     * Return an entity,Product ,with an Given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Product> get(@PathVariable Long id) {
        return new HttpEntity<>(productService.findOne(id));
    }

    /**
     * Deletes an entity with an given ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return new HttpEntity(null);
    }

    /**
     * Simple save or update an entity
     * @param product
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Product> save(@RequestBody Product product) {
        productService.save(product);
        return new HttpEntity<Product>(product);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws JRException, IOException {

        InputStream report = getClass().getResourceAsStream("/jasperreports/teste.jasper");

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap(), new JREmptyDataSource());
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=teste.pdf");

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.exportReport();

        File f = new File("teste2.pdf");
        FileOutputStream fo = new FileOutputStream(f);

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fo);
        exporter.exportReport();

        fo.flush();
        fo.close();


    }

}
