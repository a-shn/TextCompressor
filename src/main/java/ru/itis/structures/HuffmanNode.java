package ru.itis.structures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HuffmanNode {
    private Map<Integer, HuffmanNode> map;
    private List<Integer> word;
    private Integer frequency;
}
