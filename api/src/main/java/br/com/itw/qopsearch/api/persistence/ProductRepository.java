package br.com.itw.qopsearch.api.persistence;

import br.com.itw.qopsearch.api.persistence.core.ProductRepositoryCustom;
import br.com.itw.qopsearch.domain.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRUD Genreated Repository for entityProduct
 * Guick Generate class:
 * https://github.com/wdavilaneto/guick
 * Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {


    @Query("SELECT distinct p.id " +
            "FROM Product p " +
            "where p.category.id = :idCat and testCase = :testCase")
    @Cacheable("findIdsByCategoryAndTestCase")
    public Integer[] findIdsByCategoryAndTestCase(@Param("idCat") Long idCat, @Param("testCase") Integer testCase);

    @Cacheable("findByCategoryIdAndTestCase")
    public List<Product> findByCategoryIdAndTestCase(Long idCat, Integer testCase);


}