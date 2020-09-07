package ca.shopify.intern.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Category {

    ANIMALS(1,"Animals"),
    FOOD(2,"Food"),
    NATURE(3,"Nature"),
    PEOPLE(4,"People"),
    ;

    @Getter
    private Integer index;
    @Getter
    private String text;


    // 普通方法
    public static String getByIndex(int index) {
        for (Category c : Category.values()) {
            if (c.getIndex() == index) {
                return c.getText();
            }
        }
        return null;
    }
}
