package org.example.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String firstname;
    private String lastname;
    private String postCode;

    public Customer init() {
        return this.toBuilder()
                .firstname("Rachel")
                .lastname("Green")
                .postCode("2066")
                .build();
    }
}
