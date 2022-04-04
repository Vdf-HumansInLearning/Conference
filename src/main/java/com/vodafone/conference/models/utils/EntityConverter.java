package com.vodafone.conference.models.utils;

public interface EntityConverter<Entity, BaseDTO> {
    public BaseDTO convertToDTO(Entity entity);
    public Entity convertToEntity(BaseDTO baseDTO);
}