package com.fiuza.great.food.infra.repository;

import com.fiuza.great.food.infra.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {}
