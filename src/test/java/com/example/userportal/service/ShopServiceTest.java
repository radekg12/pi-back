package com.example.userportal.service;

import com.example.userportal.domain.Shop;
import com.example.userportal.repository.ShopRepository;
import com.example.userportal.service.dto.ShopDTO;
import com.example.userportal.service.mapper.ShopMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopServiceTest {

    @MockBean
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ShopService shopService;

    @Test
    public void findClosestShops_givenRandomCoordinates_shouldSucceed() {
        // given
        double latitude = 52.229675;
        double longitude = 21.012230;
        List<Shop> shops = newShopsList(latitude, longitude);
        given(shopRepository.findAll())
                .willReturn(shops);

        // when
        List<ShopDTO> result = shopService.findClosestShops(latitude, longitude);

        // then
        Assert.assertEquals(5, result.size());
        assertDistances(shopMapper.toShops(result), latitude, longitude);
    }

    private void assertDistances(List<Shop> shops, double latitude, double longitude) {
        for (int i = 0; i < shops.size() - 1; i++) {
            Assert.assertTrue(shops.get(i).calculateDistance(latitude, longitude)
                    < shops.get(i+1).calculateDistance(latitude, longitude));
        }
    }

    private List<Shop> newShopsList(double latitude, double longitude) {
        List<Shop> shops = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            shops.add(new Shop()
                    .setId(i)
                    .setLatitude(randDouble(latitude))
                    .setLongitude(randDouble(longitude))
                    .setDescription("Sklep prowadzony przez pikeja nr " + i));
        }
        return shops;
    }

    private double randDouble(double middleOfRange) {
        return ThreadLocalRandom.current().nextDouble(middleOfRange - 5, middleOfRange + 5);
    }

}
