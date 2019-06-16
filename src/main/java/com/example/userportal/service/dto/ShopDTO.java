package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private Integer id;
    private double latitude;
    private double longitude;
    private String description;
}
