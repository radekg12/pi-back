package com.example.userportal.service.impl;

import com.example.userportal.domain.Shop;
import com.example.userportal.repository.ShopRepository;
import com.example.userportal.service.ShopService;
import com.example.userportal.service.dto.ShopDTO;
import com.example.userportal.service.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    @Override
    public List<ShopDTO> findClosestShops(double latitude, double longitude) {
        List<Shop> shops = shopRepository.findAll();
        Comparator<Shop> distanceComparator = createDistanceComparator(latitude, longitude);
        shops.sort(distanceComparator);
        return shopMapper.toShopDtos(getSublist(shops));
    }

    private Comparator<Shop> createDistanceComparator(double latitude, double longitude) {
        return (s1, s2) -> {
            double distanceToFirst = s1.calculateDistance(latitude, longitude);
            double distanceToSecond = s2.calculateDistance(latitude, longitude);
            return Double.compare(distanceToFirst, distanceToSecond);
        };
    }

    private List<Shop> getSublist(List<Shop> shops) {
        if (shops.size() < 5) return shops;
        return shops.subList(0, 5);
    }

}
