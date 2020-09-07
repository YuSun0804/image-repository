package ca.shopify.intern.service;

import ca.shopify.intern.entity.Image;
import ca.shopify.intern.vo.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

public interface ImageSearchService {

    Page<Image> findByDescription(String title);

    void saveAll(List<Image> list);

    Iterator<Image> findAll();

    Page<Image> search(SearchRequest searchRequest);

    Page<Image> searchSimilar(SearchRequest searchRequest);

}
