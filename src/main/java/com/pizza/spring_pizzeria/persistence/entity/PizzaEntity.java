package com.pizza.spring_pizzeria.persistence.entity;

import com.pizza.spring_pizzeria.persistence.audit.AuditPizzaListener;
import com.pizza.spring_pizzeria.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(length = 70, nullable = false, unique = false)
    private String name;

    @Column(length = 150, nullable = false, unique = true)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "smallint")
    @Convert(converter = BooleanToSmallintConverter.class)
    private Boolean vegetarian;

    @Column(columnDefinition = "smallint")
    @Convert(converter = BooleanToSmallintConverter.class)
    private Boolean vegan;

    @Column(columnDefinition = "smallint", nullable = false)
    @Convert(converter = BooleanToSmallintConverter.class)
    private Boolean available;

    public Integer getIdPizza() {
        return idPizza;
    }

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", available=" + available +
                '}';
    }
}
