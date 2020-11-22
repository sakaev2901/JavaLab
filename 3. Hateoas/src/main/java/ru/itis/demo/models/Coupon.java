package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ProductCategory productCategory;
    private String name;
    private String description;
    private Integer percent;
    private String code;

}
