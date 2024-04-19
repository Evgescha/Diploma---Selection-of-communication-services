package com.hescha.mobile_services.service;

import java.util.List;

public interface CrudService<Entity> {

    boolean create(Entity entity);

    Entity read(long id);

    List<Entity> readAll();

    boolean update(Entity entity);

    boolean delete(long id);
}