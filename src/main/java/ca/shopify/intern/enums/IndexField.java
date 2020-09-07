package ca.shopify.intern.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IndexField {

    TITLE("title"),
    DESCRIPTION("description"),
    CATEGORY("category"),
    PRICE("price"),
    ;

    @Getter
    private String name;



}
