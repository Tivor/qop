package br.com.itw.qopsearch.api.persistence;

import java.math.BigDecimal;
import java.util.List;

import br.com.itw.commons.persistence.ICoreRepository;
import br.com.itw.qopsearch.api.persistence.core.FeatureRepositoryCustom;
import br.com.itw.qopsearch.domain.Feature;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entityFeature
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long >, FeatureRepositoryCustom  {

    @Cacheable("findByCategoryId")
    List<Feature> findByCategoryId(Long idCat);


}