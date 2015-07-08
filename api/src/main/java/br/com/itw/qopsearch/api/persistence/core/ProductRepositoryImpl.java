/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.qopsearch.api.persistence.core;

import br.com.itw.commons.persistence.PageableHelper;
import br.com.itw.qopsearch.domain.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    /**
     * Returns an full, but Paged, list of all entities (Product)
     *
     * @param pageable
     * @return
     */
    public Page<Product> findAll(Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Product.class);
        return (Page<Product>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Product> search(Product product, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Product.class);

        // Prepare Example
        Example example = Example.create(product);
        criteria.add(example.enableLike(MatchMode.ANYWHERE).ignoreCase());

        return (Page<Product>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Page<Product> searchText(String text, Pageable pageable) {
        if (pageable == null) {
            pageable = PageableHelper.deafultPageable();
        }
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.disjunction()
                        .add(Restrictions.ilike("description", text, MatchMode.ANYWHERE))
                        .add(Restrictions.ilike("image", text, MatchMode.ANYWHERE))
        );
        return (Page<Product>) PageableHelper.getPage(criteria, pageable);
    }

    @Override
    public Product get(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product update(Product product) {
        Session session = (Session) entityManager.getDelegate();
        session.update(product);
        return product;
    }

    @Override
    public Product create(Product product) {
        Session session = (Session) entityManager.getDelegate();
        session.persist(product);
        return product;
    }

}
