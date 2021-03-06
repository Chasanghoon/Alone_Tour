package com.ssafy.tourist.domain.record.db.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisitTouristName {
    private int courseDataId;
    private int touristId;
    private String touristName;
}
