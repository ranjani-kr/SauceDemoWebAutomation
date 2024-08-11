package org.example.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder(toBuilder = true)
@NoArgsConstructor @AllArgsConstructor
public class Product {
    private String name;
    private String price;
    private String description;

    public Product init() {
        return this.toBuilder()
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
}