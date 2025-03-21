package com.fiuza.great.food.helper.dto.item;

import com.fiuza.great.food.core.dto.request.item.ItemUpdateDto;

import java.math.BigDecimal;

public class ItemUpdateDtoHelper {
    public static ItemUpdateDto defaultDto() {
        return new ItemUpdateDto(
                "Vatapá",
                "Gostoso de mais",
                new BigDecimal("27.00"),
                true,
                "/foto-do-vatapa.jpg"
        );
    }
}
