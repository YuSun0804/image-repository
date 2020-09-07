package ca.shopify.intern.vo;

import lombok.Data;

@Data
public class SearchRequest {
    private String text;
    private Long lowPrice;
    private Long highPrice;
    private Integer category;
    private Integer orderBy;
    private Integer pageNo;
    private Integer size;
}
