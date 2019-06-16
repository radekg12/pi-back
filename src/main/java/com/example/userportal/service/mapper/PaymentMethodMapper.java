package com.example.userportal.service.mapper;

import com.example.userportal.domain.PaymentMethod;
import com.example.userportal.service.dto.PaymentMethodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

  PaymentMethodDTO toPaymentMethodDto(PaymentMethod paymentMethod);

  @Mapping(target = "ordersById", ignore = true)
  PaymentMethod toPaymentMethod(PaymentMethodDTO paymentMethodDTO);

  List<PaymentMethodDTO> toPaymentMethodDtos(List<PaymentMethod> paymentMethod);

  List<PaymentMethod> toPaymentMethods(List<PaymentMethodDTO> paymentMethodDTOS);

}
