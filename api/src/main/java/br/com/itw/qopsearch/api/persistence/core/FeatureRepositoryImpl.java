/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.persistence.core;

import java.math.BigDecimal;
import br.com.itw.commons.persistence.PageableHelper;
import br.com.itw.qopsearch.domain.Feature;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FeatureRepositoryImpl implements FeatureRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Feature)
     * @param pageable
     * @return
     */
    public Page<Feature> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Feature.class);
        return (Page<Feature>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Feature> search(Feature feature, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Feature.class);

        // Prepare Example
        Example example = Example.create(feature);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        return (Page<Feature>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Feature> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Feature.class);
        criteria.add( Restrictions.disjunction()
            .add(Restrictions.ilike("name", text, MatchMode.ANYWHERE))
        );
        return (Page<Feature>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Feature get(Long id) {
        return entityManager.find(Feature.class, id);
    }
    @Override
    public  Feature update( Feature feature){
        Session session = (Session) entityManager.getDelegate();
        session.update(feature);
        return feature;
    }
    @Override
    public  Feature create( Feature feature) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(feature);
        return feature;
    }

}
