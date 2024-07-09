package com.example.CustomerMapping.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@Entity
@Table(name = "CUSTOMER_INFOMATION_MAPPING")
public class CustomerInfomation {
    @Id
    @Column(name = "CUSTOMER_ID", nullable = false)
    private String customerId;
}
