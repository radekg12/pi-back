package com.example.userportal.service;

import com.example.userportal.configuration.RedisConfig;
import com.example.userportal.service.dto.ProductDTO;
import com.example.userportal.service.impl.RecommendationServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class RecommendationServiceTest {

    private static redis.embedded.RedisServer redisServer;

    @Autowired
    private RedisTemplate redisTemplate;

    @BeforeClass
    public static void startRedisServer() throws IOException {
        redisServer = new redis.embedded.RedisServer(6379);
        redisServer.start();
    }

    @AfterClass
    public static void stopRedisServer() {
        redisServer.stop();
    }

    @Test
    public void whenUpdatingRating_thenAvailableRecommendationForSingleProduct() {
        List<ProductDTO> productList = new ArrayList<>();
        List<ProductDTO> recommendationList = new ArrayList<>();
        ProductDTO product = new ProductDTO().setId(1).setName("kiełba");
        recommendationList.add(product);

        productList.add(new ProductDTO().setId(0).setName("szynka"));
        productList.add(product);
        productList.add(new ProductDTO().setId(2).setName("ser"));
        productList.add(new ProductDTO().setId(3).setName("mleko"));

        RecommendationServiceImpl recommendationService = new RecommendationServiceImpl(redisTemplate);
        recommendationService.addProductsRating(productList);
        productList.remove(0);
        productList.remove(0);
        recommendationService.addProductsRating(productList);
        Set<String> recommendations = recommendationService.getRecommendation(recommendationList, 1);
        Set<String> expectedRecommendations = new TreeSet<>(Arrays.asList("3", "2"));
        assertEquals(expectedRecommendations, recommendations);
    }

    @Test
    public void whenUpdatingRating_thenAvailableRecommendationForMultipleProduct() {
        List<ProductDTO> productList = new ArrayList<>();
        List<ProductDTO> recommendationList = new ArrayList<>();
        ProductDTO product1 = new ProductDTO().setId(1).setName("kiełba");
        ProductDTO product2 = new ProductDTO().setId(0).setName("szynka");
        recommendationList.add(product1);
        recommendationList.add(product2);

        productList.add(new ProductDTO().setId(2).setName("ser"));
        productList.add(new ProductDTO().setId(3).setName("mleko"));
        productList.add(product1);
        productList.add(product2);

        RecommendationServiceImpl recommendationService = new RecommendationServiceImpl(redisTemplate);
        recommendationService.addProductsRating(productList);
        productList.remove(0);
        recommendationService.addProductsRating(productList);
        Set<String> recommendations = recommendationService.getRecommendation(recommendationList, 0);
        Set<String> expectedRecommendations = new TreeSet<>();
        expectedRecommendations.add("3");
        assertEquals(expectedRecommendations, recommendations);
    }

}
