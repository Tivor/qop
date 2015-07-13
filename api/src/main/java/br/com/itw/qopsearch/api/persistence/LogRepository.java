package br.com.itw.qopsearch.api.persistence;

import br.com.itw.qopsearch.domain.AccessLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRUD Genreated Repository for entityProduct
 * Guick Generate class:
 * https://github.com/wdavilaneto/guick
 * Author: service-wdavilaneto@redhat.com
 */
@Repository
public interface LogRepository extends JpaRepository<AccessLog, Long> {

    public AccessLog findByLoginAndOperation(String login, Integer operation);

}