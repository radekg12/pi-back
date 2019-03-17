package com.example.userportal.controller;

import com.example.userportal.domain.DeliveryType;
import com.example.userportal.repository.DeliveryTypeRepository;
import com.example.userportal.service.DeliveryTypeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryTypeControllerTest {

  @Autowired
  DeliveryTypeService service;
  @Autowired
  private MockMvc mvc;
  @MockBean
  private DeliveryTypeRepository repository;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void givenDeliveryTypes_whenGetDeliveryType_thenReturnJsonArray()
          throws Exception {

    //given
    ArrayList<DeliveryType> deliveryTypes = new ArrayList<>();
    deliveryTypes.add(new DeliveryType()
            .setId(1)
            .setName("kurier")
            .setPrice(20));

    deliveryTypes.add(new DeliveryType()
            .setId(2)
            .setName("odbiór osobisty")
            .setPrice(0));

    Mockito.when(repository.findAll()).thenReturn(deliveryTypes);

    //when
    mvc.perform(get("/deliveryType")
            .contentType(MediaType.APPLICATION_JSON))

            //then
            .andExpect(status().isOk())
            .andExpect(content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", Matchers.hasSize(2)))
            .andExpect(jsonPath("$[0].name", Matchers.is("kurier")))
            .andExpect(jsonPath("$[1].name", Matchers.is("odbiór osobisty")));
  }
}
