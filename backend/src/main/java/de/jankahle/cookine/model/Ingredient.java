package de.jankahle.cookine.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {
    @EqualsAndHashCode.Exclude
    private String id;
    private String name;
    private String amount;
    private String measurementUnit;
}
