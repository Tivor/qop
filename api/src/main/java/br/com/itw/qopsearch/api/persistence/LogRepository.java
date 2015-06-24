package br.com.itw.qopsearch.api.persistence;

import br.com.itw.qopsearch.domain.AccessLog;
import br.com.itw.qopsearch.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  CRUD Genreated Repository for entityProduct
 *  Guick Generate class:
 *  https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface LogRepository extends JpaRepository<AccessLog, Long> {



}