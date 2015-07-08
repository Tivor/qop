/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.rest;

import br.com.itw.qopsearch.api.persistence.CategoryRepository;
import br.com.itw.qopsearch.api.persistence.FeatureRepository;
import br.com.itw.qopsearch.api.persistence.LogRepository;
import br.com.itw.qopsearch.api.persistence.ProductRepository;
import br.com.itw.qopsearch.domain.AccessLog;
import br.com.itw.qopsearch.domain.Category;
import br.com.itw.qopsearch.domain.Feature;
import br.com.itw.qopsearch.domain.dto.FeatureFilter;
import br.com.itw.qopsearch.domain.dto.ProductResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CRUD Rest Json 'Controller' for entityProduct
 * Guick Generate class:
 * https://github.com/wdavilaneto/guick
 * Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value = "/Product")
public class ProductController {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0, 20);

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private static Map<String, Integer> operationMap = new HashMap();

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
    }

    @Resource
    private ProductRepository productRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private FeatureRepository featureRepository;

    @Resource
    private LogRepository logRepository;

    @RequestMapping(value = "/savedSurvey/{login}", method = RequestMethod.GET)
    public HttpEntity<Map> getSavedSurvey(@PathVariable String login) throws IOException {

        AccessLog accessLog = logRepository.findByLoginAndOperation(login, Integer.valueOf(operationMap.get("survey")));

        Map survey = new HashMap();
        if (accessLog != null) {
            ObjectMapper mapper = new ObjectMapper();
            survey = (HashMap) mapper.readValue(accessLog.getParams(), HashMap.class);
        }
        return new HttpEntity(survey);

    }

    @RequestMapping(value = "/logSurvey", method = RequestMethod.POST)
    public ResponseEntity logSurvey(@RequestBody HashMap answers) throws JsonProcessingException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer survey = Integer.valueOf(operationMap.get("survey"));
        AccessLog accessLog = logRepository.findByLoginAndOperation(principal.toString(), survey);

        if (accessLog == null) {
            accessLog = new AccessLog();
        }

        String json = getJsonAsString(answers);

        return logOperation(json, accessLog, survey, principal.toString());

    }


    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public ResponseEntity log(@RequestBody HashMap<String, Object> params) throws JsonProcessingException {

        String json = getJsonAsString(params);

        AccessLog accessLog = new AccessLog();

        Integer operation = operationMap.get((String) params.get("op"));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = principal.toString();
        return logOperation(json, accessLog, operation, login);
    }

    private String getJsonAsString(HashMap params) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(params);
    }

    @Transactional
    private ResponseEntity logOperation(String json, AccessLog accessLog, Integer operation, String login) {
        accessLog.setLogin(login);
        accessLog.setOperation(operation);

        accessLog.setParams(json);
        accessLog.setRegister(Calendar.getInstance().getTime());

        logRepository.save(accessLog);
        return new ResponseEntity(HttpStatus.OK);
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
    @RequestMapping(value = "/findAll/{idCat}/{testCase}", method = RequestMethod.GET)
    public HttpEntity<ProductResult> findAll(@PathVariable Long idCat, @PathVariable Integer testCase) {

        ProductResult result = new ProductResult(
                productRepository.findIdsByCategoryAndTestCase(idCat, testCase),
                productRepository.findByCategoryIdAndTestCase(idCat, testCase)
        );

        return new HttpEntity(result);
    }

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    @Cacheable("getCategories")
    public HttpEntity<List<Category>> getCategories() {

        List<Category> categoryList = categoryRepository.findAll();
        return new HttpEntity(categoryList);
    }

    @RequestMapping(value = "/getFeatures/{idCat}", method = RequestMethod.GET)
    public HttpEntity<List<Feature>> getFeatures(@PathVariable Long idCat) {

        return new HttpEntity(featureRepository.findByCategoryId(idCat));
    }
}
