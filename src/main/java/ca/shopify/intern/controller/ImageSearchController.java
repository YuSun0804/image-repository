package ca.shopify.intern.controller;

import ca.shopify.intern.entity.Image;
import ca.shopify.intern.enums.Category;
import ca.shopify.intern.service.ImageSearchService;
import ca.shopify.intern.vo.SearchRequest;
import ca.shopify.intern.vo.SearchResponse;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/elastic")
public class ImageSearchController {

    @Autowired
    private ImageSearchService imageSearchService;

    @GetMapping("/init")
    public void init() {
        List<Image> list = new ArrayList<>();
        list.add(new Image(1L, "cat", "Orange tabby cat", Category.ANIMALS.getIndex(), 199L, "upload/cat.jpg"));
        list.add(new Image(2L, "cows", "Farm animals", Category.ANIMALS.getIndex(), 299L, "upload/cows.jpg"));
        list.add(new Image(3L, "dog", "Happy corgin", Category.ANIMALS.getIndex(), 399L, "upload/dog.jpg"));
        list.add(new Image(4L, "cupcakes", "Frosting cupcakes", Category.FOOD.getIndex(), 189L, "upload/cupcakes.jpg"));
        list.add(new Image(5L, "eclair", "Strawberry pastries", Category.FOOD.getIndex(), 899L, "upload/eclair.jpg"));
        list.add(new Image(6L, "lake", "Beautiful lakes", Category.NATURE.getIndex(), 1299L, "upload/lake.jpg"));
        list.add(new Image(7L, "river", "Beautiful lakes", Category.NATURE.getIndex(), 799L, "upload/polynesia.jpg"));
        list.add(new Image(8L, "model", "fashion model", Category.PEOPLE.getIndex(), 699L, "upload/model.jpg"));
        list.add(new Image(9L, "guitar", "Girl with guitar", Category.PEOPLE.getIndex(), 599L, "upload/guitar.jpg"));

        imageSearchService.saveAll(list);

    }

    @GetMapping("/all")
    public Iterator<Image> all() {
        return imageSearchService.findAll();
    }

    @GetMapping("/desc")
    public Page<Image> desc(String desc) {
        return imageSearchService.findByDescription(desc);
    }

    @PostMapping("/search")
    public List<SearchResponse> search(@RequestBody SearchRequest searchRequest) {
        processRequest(searchRequest);
        Page<Image> result = imageSearchService.search(searchRequest);
        return getSearchResponse(result);
    }

    @PostMapping("/searchSimilar")
    public List<SearchResponse> searchSimilar(@RequestBody SearchRequest searchRequest) {
        processRequest(searchRequest);
        Page<Image> result = imageSearchService.searchSimilar(searchRequest);
        return getSearchResponse(result);
    }

    private List<SearchResponse> getSearchResponse(Page<Image> result) {
        List<Image> content = result.getContent();
        List<SearchResponse> searchResponseList = Lists.newArrayList();
        for (Image image : content) {
            SearchResponse searchResponse = new SearchResponse();
            searchResponse.setTitle(image.getTitle());
            searchResponse.setDescription(image.getDescription());
            searchResponse.setCategory(Category.getByIndex(image.getCategory()));
            searchResponse.setPrice(image.getPrice() / 100.0);
            searchResponse.setUrl(image.getUrl());
            searchResponseList.add(searchResponse);
        }
        return searchResponseList;
    }

    private void processRequest(SearchRequest searchRequest) {
        if (searchRequest.getPageNo() == null) {
            searchRequest.setPageNo(0);
        }

        if (searchRequest.getSize() == null) {
            searchRequest.setSize(10);
        }

        if (searchRequest.getLowPrice() == null) {
            searchRequest.setLowPrice(0L);
        }

        if (searchRequest.getHighPrice() == null) {
            searchRequest.setHighPrice(9999L);
        }

        if (searchRequest.getOrderBy() == null) {
            searchRequest.setOrderBy(1);
        }
    }
}
