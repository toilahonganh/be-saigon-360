package com.be_servicie.saigon_travel.be_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class for mapping DTO to Entity.
 * @param <D> Dto type
 * @param <E> Entity type
 */
public abstract class EntityMapper<D, E> {
    public abstract E toEntity(D dto);

    public List<E> toListEntity(List<D> dtoList) {
        if(dtoList == null) {
            return null;
        }
        return dtoList.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
