package ca.shopify.intern.vo;

import lombok.Data;

@Data
public class SearchResponse {
    private String title;
    private Double price;
    private String category;
    private String url;
    private String description;
}
