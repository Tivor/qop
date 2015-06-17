/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.rest.reports;

import br.com.itw.qopsearch.domain.dto.HistogramDto;
import br.com.itw.qopsearch.api.persistence.mybatis.DashboardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  CRUD Rest Json 'Controller' for entityProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@RestController
@RequestMapping(value="/reports/dashboard")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Resource(name = "dashboardRepository")
    private DashboardRepository dashboardRepository;

    @RequestMapping(value = "/allCount", method = RequestMethod.GET)
    public List<HistogramDto> allCount(){
        return dashboardRepository.allCount();
    }

    @RequestMapping(value = "/getFeatureProductCollectionWithSumFeatureSpecificationValue", method = RequestMethod.GET)
    List<HistogramDto> getFeatureProductCollectionWithSumFeatureSpecificationValue() {
        return dashboardRepository.getFeatureProductCollectionWithSumFeatureSpecificationValue();
    }

    @RequestMapping(value = "/getFeatureProductCollectionWithSumProductSpecificationValue", method = RequestMethod.GET)
    List<HistogramDto> getFeatureProductCollectionWithSumProductSpecificationValue() {
        return dashboardRepository.getFeatureProductCollectionWithSumProductSpecificationValue();
    }


}
