package br.com.itw.qopsearch.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by thiago.gama on 01/10/2014.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEntity<T extends Serializable> {


    /**
     * Returns the identifier of the document.
     *
     * @return the id
     */
    public abstract T getId();

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getId() == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        return this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId().hashCode();
    }
}
