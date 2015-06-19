package br.com.itw.qopsearch.api.persistence;

import br.com.itw.qopsearch.api.persistence.core.ProductRepositoryCustom;
import br.com.itw.qopsearch.domain.Category;
import br.com.itw.qopsearch.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  CRUD Genreated Repository for entityProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}