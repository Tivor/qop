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
import java.util.List;

import br.com.itw.commons.rest.dto.Pagination;
import br.com.itw.commons.rest.dto.SearchFilter;
import br.com.itw.qopsearch.api.persistence.CategoryRepository;
import br.com.itw.qopsearch.api.persistence.FeatureRepository;
import br.com.itw.qopsearch.api.persistence.ProductRepository;
import br.com.itw.qopsearch.domain.Category;
import br.com.itw.qopsearch.domain.Feature;
import br.com.itw.qopsearch.domain.Product;
import br.com.itw.qopsearch.api.service.IProductService;
import br.com.itw.qopsearch.domain.dto.FeatureFilter;
import br.com.itw.qopsearch.domain.dto.ProductResult;
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

    @Resource
    private ProductRepository productRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private FeatureRepository featureRepository;

    @RequestMapping(value = "/refine", method = RequestMethod.POST)
    public HttpEntity<ProductResult> refine(@RequestBody List<FeatureFilter> filter) {

        System.out.println(filter);

        return null;
    }

    /**
     * Returns an full, but Paged, list of all entities (Product)
     * @return
     */
    @RequestMapping(value = "/findAll/{idCat}/{testCase}", method = RequestMethod.GET)
    public HttpEntity<ProductResult> findAll(@PathVariable Long idCat, @PathVariable Integer testCase) {

        ProductResult result = new ProductResult(
                productRepository.findIdsByCategoryAndTestCase(idCat, testCase),
                productRepository.findByCategoryIdAndTestCase(idCat, testCase)
        );

        return new HttpEntity(result);
    }

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    public HttpEntity<List<Category>> getCategories() {

        return new HttpEntity(categoryRepository.findAll());
    }

    @RequestMapping(value = "/getFeatures/{idCat}", method = RequestMethod.GET)
    public HttpEntity<List<Feature>> getFeatures(@PathVariable Long idCat) {

        return new HttpEntity(featureRepository.findByCategoryId(idCat));
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
