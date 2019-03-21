package com.ybwx.stock.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRange {

    Integer rangeMin ;
    Integer rangeMax ;
    Integer value    ;
}
