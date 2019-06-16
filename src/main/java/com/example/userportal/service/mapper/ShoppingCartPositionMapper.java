package com.example.userportal.service.mapper;

import com.example.userportal.domain.ShoppingCartPosition;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface ShoppingCartPositionMapper {

  @Mapping(target = "product", source = "product")
  ShoppingCartPositionDTO toShoppingCartPositionDto(ShoppingCartPosition shoppingCartPosition);

  @Mapping(target = "customer", ignore = true)
  @Mapping(target = "product", source = "product")
  ShoppingCartPosition toShoppingCartPosition(ShoppingCartPositionDTO shoppingCartPositionDto);

  List<ShoppingCartPositionDTO> toShoppingCartPositionDtos(List<ShoppingCartPosition> shoppingCartPositions);

  List<ShoppingCartPosition> toShoppingCartPositions(List<ShoppingCartPositionDTO> shoppingCartPositionDtos);
}
