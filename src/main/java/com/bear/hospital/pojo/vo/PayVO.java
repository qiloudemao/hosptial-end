package com.bear.hospital.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayVO {
    private String itemsAndPrice;
    private String drugsAndPrice;
    private double totolPrice;
}

