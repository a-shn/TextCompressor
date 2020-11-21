package ru.itis.structures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Node {
    private Integer offset;
    private Integer length;
    private Integer symbol;
}
