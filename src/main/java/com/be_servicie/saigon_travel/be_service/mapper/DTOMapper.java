package com.be_servicie.saigon_travel.be_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class for mapping entity to DTO.
 * @param <E> Entity type
 * @param <D> DTO type
*/
public abstract class DTOMapper<E, D> {
    public abstract D toDto(E entity);

    public List<D> toListDto(List<E> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}