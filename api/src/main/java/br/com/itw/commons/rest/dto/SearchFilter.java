/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.commons.rest.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * This class represents an Search filter of a given Entity or DTO. The content Property contains the entity( or dto)
 * and the Pagination is just an dto to wrap request pagination parameters to create an Spring Pageable..
 * TODO: Staging class!
 */
public class SearchFilter<T> {

    private static PageRequest DEFAULT_PAGE = new PageRequest(0, 10);

    private Pagination pagination;

    private T content;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pageable) {
        this.pagination = pageable;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Pageable getPageable() {
        if (pagination == null) {
            pagination = new Pagination();
            pagination.setPage(1);
            pagination.setSize(10);
        }
        return pagination.getPageable();
    }
}
