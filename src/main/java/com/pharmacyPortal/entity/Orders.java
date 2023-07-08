package com.pharmacyPortal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String mobileNo;
    private String prescription;
    private Double amount;
    private String address;
    private Integer distributorId;
    private String orderStatus;
    private String deliveredStatus;
    private Integer quantity;
    @ManyToOne
    private User user;

    @OneToOne
    private Medicine medicine;
}
