package com.juanjoea.starWars.service;

import java.util.List;

import com.juanjoea.starWars.dto.SortableDto;

public interface SortableEntityService {

    List<? extends SortableDto> sort(List<? extends SortableDto> list, String field, String order);
}
