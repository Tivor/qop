/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.persistence.core;

import br.com.itw.commons.persistence.PageableHelper;
import br.com.itw.qopsearch.domain.ProductFeature;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class FeatureProductRepositoryImpl implements FeatureProductRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (FeatureProduct)
     * @param pageable
     * @return
     */
    public Page<ProductFeature> findAll(Pageable pageable){
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ProductFeature.class);
        return (Page<ProductFeature>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<ProductFeature> search(ProductFeature productFeature, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ProductFeature.class);

        // Prepare Example
        Example example = Example.create(productFeature);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        if (productFeature.getFeature() != null && productFeature.getFeature().getId() != null) {
            criteria.add(Restrictions.eq("feature.id", productFeature.getFeature().getId()));
        }

        if (productFeature.getProduct() != null && productFeature.getProduct().getId() != null) {
            criteria.add(Restrictions.eq("product.id", productFeature.getProduct().getId()));
        }

        return (Page<ProductFeature>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<ProductFeature> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(ProductFeature.class);
        criteria.add( Restrictions.disjunction()
        );
        return (Page<ProductFeature>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public ProductFeature get(Long id) {
        return entityManager.find(ProductFeature.class, id);
    }
    @Override
    public ProductFeature update( ProductFeature productFeature){
        Session session = (Session) entityManager.getDelegate();
        session.update(productFeature);
        return productFeature;
    }
    @Override
    public ProductFeature create( ProductFeature productFeature) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(productFeature);
        return productFeature;
    }

}
