/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.service.core;

import java.math.BigDecimal;
import java.util.Date;
import br.com.itw.qopsearch.domain.Product;
import br.com.itw.qopsearch.api.persistence.ProductRepository;
import br.com.itw.qopsearch.api.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  CRUD Rest Json 'Controller' for entityProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Service
class ProductService implements IProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Resource(name = "productRepository")
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> search(Product product , Pageable pageable) {
        return productRepository.search(product, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> searchText(String text , Pageable pageable) {
        return productRepository.searchText(text, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        if (product.getId() != null){
            productRepository.update(product);
        }
        return productRepository.create(product);
    }


}
