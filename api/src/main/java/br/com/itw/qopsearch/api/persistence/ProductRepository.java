package br.com.itw.qopsearch.api.persistence;

import java.math.BigDecimal;
import br.com.itw.commons.persistence.ICoreRepository;
import br.com.itw.qopsearch.api.persistence.core.ProductRepositoryCustom;
import br.com.itw.qopsearch.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entityProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long >, ProductRepositoryCustom  {


}