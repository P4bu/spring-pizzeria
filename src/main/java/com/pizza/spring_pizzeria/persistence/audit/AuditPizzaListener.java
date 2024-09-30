package com.pizza.spring_pizzeria.persistence.audit;

import com.pizza.spring_pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.apache.commons.lang3.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity){
        System.out.println("Post Load");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {

        System.out.println("Post Update");
        System.out.println("Old value" + this.currentValue);
        System.out.println("New value" + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println(entity.toString());
    }


}
