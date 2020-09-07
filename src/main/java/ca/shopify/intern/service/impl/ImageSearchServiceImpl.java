package ca.shopify.intern.service.impl;

import ca.shopify.intern.entity.Image;
import ca.shopify.intern.enums.IndexField;
import ca.shopify.intern.repository.ImageRepository;
import ca.shopify.intern.service.ImageSearchService;
import ca.shopify.intern.vo.SearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ImageSearchServiceImpl implements ImageSearchService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Page<Image> findByDescription(String title) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "title"));
        return imageRepository.findByDescription(title, pageable);
    }

    @Override
    public void saveAll(List<Image> list) {
        imageRepository.saveAll(list);
    }

    @Override
    public Iterator<Image> findAll() {
        return imageRepository.findAll().iterator();
    }

    @Override
    public Page<Image> search(SearchRequest searchRequest) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder = boolQueryBuilder
                .must(QueryBuilders.rangeQuery(IndexField.PRICE.getName()).gte(searchRequest.getLowPrice() * 100).lte(searchRequest.getHighPrice() * 100));

        if (StringUtils.isNotBlank(searchRequest.getText())) {
            boolQueryBuilder = boolQueryBuilder
                    .should(QueryBuilders.termQuery(IndexField.TITLE.getName(), searchRequest.getText()))
                    .should(QueryBuilders.matchQuery(IndexField.DESCRIPTION.getName(), searchRequest.getText()));
            boolQueryBuilder.minimumShouldMatch(1);
        }

        if (searchRequest.getCategory() != null && searchRequest.getCategory() != 0) {
            boolQueryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery(IndexField.CATEGORY.getName(), searchRequest.getCategory()));
        }

        Pageable pageable = PageRequest.of(searchRequest.getPageNo(), searchRequest.getSize(), getSortBy(searchRequest.getOrderBy()));

//        System.out.println(boolQueryBuilder.toString());
        return imageRepository.search(boolQueryBuilder, pageable);
    }

    @Override
    public Page<Image> searchSimilar(SearchRequest searchRequest) {
        Image image = imageRepository.findByTitle(searchRequest.getText());
        Pageable pageable = PageRequest.of(searchRequest.getPageNo(), searchRequest.getSize(), getSortBy(searchRequest.getOrderBy()));
        String[] fields = {IndexField.TITLE.getName(), IndexField.DESCRIPTION.getName()};

        return imageRepository.searchSimilar(image, fields, pageable);
    }

    private Sort getSortBy(Integer orderBy) {
        switch (orderBy) {
            case 2:
                return Sort.by(Sort.Direction.DESC, IndexField.TITLE.getName());
            case 3:
                return Sort.by(Sort.Direction.ASC, IndexField.PRICE.getName());
            case 4:
                return Sort.by(Sort.Direction.DESC, IndexField.PRICE.getName());
            case 1:
            default:
                return Sort.by(Sort.Direction.ASC, IndexField.TITLE.getName());
        }
    }

}
