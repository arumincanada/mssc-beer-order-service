package com.learning.beer.order.service.services;

import com.learning.beer.order.service.domain.BeerOrder;

public interface BeerOrderManager {
    BeerOrder newBeerOrder(BeerOrder beerOrder);
}
