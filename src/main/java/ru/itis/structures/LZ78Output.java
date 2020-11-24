package ru.itis.structures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LZ78Output {
    private List<LZ78Node> nodes;
    private Integer alphabetSize;
    private Integer dictSize;
}
