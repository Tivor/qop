package br.com.itw.qopsearch.api.persistence;

import br.com.itw.qopsearch.api.persistence.core.FeatureRepositoryCustom;
import br.com.itw.qopsearch.domain.Feature;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRUD Genreated Repository for entityFeature
 * Guick Generate class:
 * https://github.com/wdavilaneto/guick
 * Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>, FeatureRepositoryCustom {

    @Cacheable("findFeatureByCategoryId")
    List<Feature> findByCategoryIdAndTestCase(Long idCat, Integer testCase);


}