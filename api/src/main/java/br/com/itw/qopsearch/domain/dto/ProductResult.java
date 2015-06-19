package br.com.itw.qopsearch.domain.dto;

import br.com.itw.qopsearch.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Igor on 18/06/2015.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResult {

    private Integer[] selectedProducts;
    private List<Product> refinementResult;

}
