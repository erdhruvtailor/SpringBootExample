package com.dt.springbootrestapi.errors;

public class ProductNotFoundException  extends RuntimeException {

    private Long id;

    public ProductNotFoundException(Long id) {
        super("Could not find item " + id);
    }
}
