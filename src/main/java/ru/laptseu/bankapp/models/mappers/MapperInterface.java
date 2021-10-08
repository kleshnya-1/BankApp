package ru.laptseu.bankapp.models.mappers;

import ru.laptseu.bankapp.models.Entity;
import ru.laptseu.bankapp.models.dto.EntityDto;

public interface MapperInterface {
    Entity map(EntityDto e);
    EntityDto map(Entity e);
}
