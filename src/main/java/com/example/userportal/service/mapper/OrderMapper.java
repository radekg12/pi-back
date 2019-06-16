package com.example.userportal.service.mapper;

import com.example.userportal.domain.Order;
import com.example.userportal.service.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderStatusMapper.class, DeliveryTypeMapper.class, PaymentMethodMapper.class, AddressMapper.class, OrderPositionMapper.class})
public interface OrderMapper {

  @Mapping(target = "address", source = "addressByDeliveryAddressId")
  @Mapping(target = "orderStatus", source = "orderStatusByOrderStatusId")
  @Mapping(target = "deliveryType", source = "deliveryTypeByDeliveryTypeId")
  @Mapping(target = "paymentMethod", source = "paymentMethodByPaymentMethodId")
  @Mapping(target = "orderPositions", source = "orderPositionsById")
  OrderDTO toOrderDto(Order order);

  @Mapping(target = "customerByCustomerId", ignore = true)
  @Mapping(target = "addressByDeliveryAddressId", source = "address")
  @Mapping(target = "orderStatusByOrderStatusId", source = "orderStatus")
  @Mapping(target = "deliveryTypeByDeliveryTypeId", source = "deliveryType")
  @Mapping(target = "paymentMethodByPaymentMethodId", source = "paymentMethod")
  @Mapping(target = "orderPositionsById", source = "orderPositions")
  Order toOrder(OrderDTO orderDTO);

  List<OrderDTO> toOrderDtos(List<Order> orders);

  List<Order> toOrders(List<OrderDTO> orderDTOS);
}
