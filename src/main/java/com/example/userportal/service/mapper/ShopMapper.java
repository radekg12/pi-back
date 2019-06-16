package com.example.userportal.service.mapper;

import com.example.userportal.domain.Shop;
import com.example.userportal.service.dto.ShopDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    ShopMapper MAPPER = Mappers.getMapper(ShopMapper.class);

    ShopDTO toShopDto(Shop shop);

    Shop toShop(ShopDTO shopDTO);

    List<ShopDTO> toShopDtos(List<Shop> shops);

    List<Shop> toShops(List<ShopDTO> shopDTOS);

}
