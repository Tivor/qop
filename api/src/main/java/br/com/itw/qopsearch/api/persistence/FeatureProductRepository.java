package br.com.itw.qopsearch.api.persistence;

import br.com.itw.qopsearch.api.persistence.core.FeatureProductRepositoryCustom;
import br.com.itw.qopsearch.domain.ProductFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entityFeatureProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface FeatureProductRepository extends JpaRepository<ProductFeature, Long >, FeatureProductRepositoryCustom  {


}