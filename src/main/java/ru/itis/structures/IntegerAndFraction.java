package ru.itis.structures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class IntegerAndFraction {
    private Integer integer;
    private Fraction fraction;
}
