package com.pizza.spring_pizzeria.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class AuditableEntity {

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "created_by")
    @CreatedDate
    private String createBy;

    @Column(name = "modified_by")
    @LastModifiedDate
    private String modfiedBy;

}
