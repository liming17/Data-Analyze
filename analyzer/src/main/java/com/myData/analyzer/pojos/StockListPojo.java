package com.myData.analyzer.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockListPojo {
    private Long id;
    private String listName;
    private List<Long> stockId;

}
