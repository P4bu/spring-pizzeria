package com.pizza.spring_pizzeria.persistence.repository;

import com.pizza.spring_pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
