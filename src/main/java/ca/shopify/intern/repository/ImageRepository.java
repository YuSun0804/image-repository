package ca.shopify.intern.repository;

import ca.shopify.intern.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImageRepository extends ElasticsearchRepository<Image, Long> {

    Page<Image> findByDescription(String desc, Pageable pageable);

    Image findByTitle(String title);

}
