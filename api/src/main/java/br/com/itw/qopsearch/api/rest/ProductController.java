/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.rest;

import br.com.itw.qopsearch.api.persistence.*;
import br.com.itw.qopsearch.api.service.IProductService;
import br.com.itw.qopsearch.domain.*;
import br.com.itw.qopsearch.domain.dto.FeatureFilter;
import br.com.itw.qopsearch.domain.dto.ProductResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * CRUD Rest Json 'Controller' for entityProduct
 * Guick Generate class:
 * https://github.com/wdavilaneto/guick
 * Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value = "/Product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    public static final int TEST_CASE_ALL = 3;
    public static final int TEST_CASE_FEATURE = 1;
    public static final int TEST_CASE_NEED = 2;

    private static Map<String, Integer> operationMap = new HashMap();

    @Resource
    private IProductService productService;

    static {
        operationMap.put("survey", Integer.valueOf(0));
        operationMap.put("newTest", Integer.valueOf(1));
        operationMap.put("changeCategory", Integer.valueOf(2));
        operationMap.put("doPaging", Integer.valueOf(3));
        operationMap.put("changeFilterRange", Integer.valueOf(4));
        operationMap.put("changeFilterOptions", Integer.valueOf(5));
        operationMap.put("changeFilterNeeds", Integer.valueOf(6));
        operationMap.put("addToCart", Integer.valueOf(7));
        operationMap.put("addToWishlist", Integer.valueOf(8));
        operationMap.put("showDetails", Integer.valueOf(9));
        operationMap.put("addToCartDetail", Integer.valueOf(10));
        operationMap.put("changeOrder", Integer.valueOf(11));
    }

    @Resource
    private ProductRepository productRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private FeatureRepository featureRepository;

    @Resource
    private LogRepository logRepository;

    @Resource
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    public HttpEntity<List<Question>> getQuestions() {
        return new HttpEntity(questionRepository.findAll(new Sort("id")));
    }

    @RequestMapping(value = "/savedSurvey", method = RequestMethod.GET)
    public HttpEntity<Map> getSavedSurvey() throws IOException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccessLog accessLog = logRepository.findByLoginAndOperation(principal.toString(), Integer.valueOf(operationMap.get("survey")));

        Map survey = new HashMap();

        if (accessLog != null) {
            ObjectMapper mapper = new ObjectMapper();
            survey = mapper.readValue(accessLog.getParams(), HashMap.class);
        }

        return new HttpEntity(survey);

    }

    @RequestMapping(value = "/logSurvey", method = RequestMethod.POST)
    public ResponseEntity logSurvey(@RequestBody HashMap answers) throws JsonProcessingException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer survey = Integer.valueOf(operationMap.get("survey"));
        String json = getJsonAsString(answers);

        productService.logSurvey(json, survey, principal.toString());
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public ResponseEntity log(@RequestBody HashMap<String, Object> params) throws JsonProcessingException {

        String json = getJsonAsString(params);

        Integer operation = operationMap.get((String) params.get("op"));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productService.logOperation(json, operation, principal.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    private String getJsonAsString(HashMap params) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(params);
    }


    @RequestMapping(value = "/refine", method = RequestMethod.POST)
    public HttpEntity<ProductResult> refine(@RequestBody List<FeatureFilter> filter) {

        System.out.println(filter);

        return null;
    }

    /**
     * Returns an full, but Paged, list of all entities (Product)
     *
     * @return
     */
    @RequestMapping(value = "/findAll/{idCat}", method = RequestMethod.GET)
    public HttpEntity<List<Product>> findAll(@PathVariable Long idCat) {
        return new HttpEntity(productRepository.findByCategoryId(idCat));
    }

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    @Cacheable("getCategories")
    public HttpEntity<List<Category>> getCategories() {

        List<Category> categoryList = categoryRepository.findAll();
        return new HttpEntity(categoryList);
    }

    @RequestMapping(value = "/getFeatures/{idCat}/{testCase}", method = RequestMethod.GET)
    public HttpEntity<List<Feature>> getFeatures(@PathVariable Long idCat, @PathVariable Integer testCase) {

        List<Feature> features = new ArrayList();

        if (Integer.valueOf(TEST_CASE_ALL).equals(testCase)) {
            features.addAll(featureRepository.findByCategoryIdAndTestCase(idCat, Integer.valueOf(TEST_CASE_FEATURE)));
            features.addAll(featureRepository.findByCategoryIdAndTestCase(idCat, Integer.valueOf(TEST_CASE_NEED)));

            Collections.shuffle(features);
        } else {
            features = featureRepository.findByCategoryIdAndTestCase(idCat, testCase);

        }
        return new HttpEntity(features);

    }
}
