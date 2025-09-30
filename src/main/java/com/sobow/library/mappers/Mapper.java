package com.sobow.library.mappers;

public interface Mapper<Entity, DTO> {
    
    DTO mapToDto(Entity entity);
    
    Entity mapFromDto(DTO dto);
}
