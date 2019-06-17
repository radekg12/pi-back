package com.example.userportal.service.mapper;

import com.example.userportal.domain.Order;
import com.example.userportal.service.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        OrderStatusMapper.class, DeliveryTypeMapper.class,
        PaymentMethodMapper.class, AddressMapper.class, OrderPositionMapper.class
})
public interface OrderMapper {

  @Mapping(target = "address", source = "address")
  @Mapping(target = "orderStatus", source = "orderStatus")
  @Mapping(target = "deliveryType", source = "deliveryType")
  @Mapping(target = "paymentMethod", source = "paymentMethod")
  @Mapping(target = "orderPositions", source = "orderPositions")
  OrderDTO toOrderDto(Order order);

  @Mapping(target = "customer", ignore = true)
  @Mapping(target = "address", source = "address")
  @Mapping(target = "orderStatus", source = "orderStatus")
  @Mapping(target = "deliveryType", source = "deliveryType")
  @Mapping(target = "paymentMethod", source = "paymentMethod")
  @Mapping(target = "orderPositions", source = "orderPositions")
  Order toOrder(OrderDTO orderDTO);

  List<OrderDTO> toOrderDtos(List<Order> orders);

  List<Order> toOrders(List<OrderDTO> orderDTOS);
}
