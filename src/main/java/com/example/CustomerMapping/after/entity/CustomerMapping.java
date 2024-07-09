package com.example.CustomerMapping.after.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_info_mapping")
public class CustomerMapping {
    @Id
    private String guid;
    private String customer_no;
    private String channel;
    private String hash_id;
    private Date create_date;
    private String inputter;
    private String checker;
    private String udf1;
    private String udf2;
    private String udf3;
    private String udf4;

}
