package ca.shopify.intern.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "image", shards = 3)
public class Image {

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_max_word" , searchAnalyzer = "ik_smart")
    private String description;
    @Field(type = FieldType.Integer)
    private Integer category;
    @Field(type = FieldType.Long)
    private Long price; // actual price * 100
    @Field(type = FieldType.Keyword,index = false)
    private String url;

}
