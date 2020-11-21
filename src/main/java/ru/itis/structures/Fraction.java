package ru.itis.structures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Fraction {
    private Integer numerator;
    private Integer denominator;
}
