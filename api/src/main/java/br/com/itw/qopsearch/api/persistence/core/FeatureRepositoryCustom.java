/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.persistence.core;

import br.com.itw.qopsearch.domain.Feature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeatureRepositoryCustom {

    /**
     * Returns an full, but Paged, list of all entities (Feature)
     *
     * @param pageable
     * @return
     */
    public Page<Feature> findAll(Pageable pageable);

    Page<Feature> search(Feature entity, Pageable pageable);

    Page<Feature> searchText(String text, Pageable pageable);

    Feature get(Long id);

    public Feature update(Feature feature);

    public Feature create(Feature feature);

}
