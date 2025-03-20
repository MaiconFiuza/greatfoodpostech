package com.fiuza.great.food.infra.repository;

import com.fiuza.great.food.infra.model.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {}
