package com.example.CustomerMapping.entity.viviet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer_test")
public class CustomerTest {
    @Id
    private Long customer_id;

    private String customer_no;
    private Long customer_class;
    private String customer_name;
    private String address_line;
    private String mobile_phone;
    private String home_phone;
    private String email;
    private Long place_id;
    private Long nationality_id;
    private String branch_code;
    private Long identify_id;
    private String unique_id;
    private Date date_of_issue;
    private String place_of_issue;
    private Character auth_stat;
    private String maker_type;
    private Long mod_no;
    private Date maker_date;
    private String checker_type;
    private String checker_id;
    private Date checker_date;
    private Date init_date;
    private Long longitude;
    private Long latitude;
    private Long package_default;
    private String isfee_default;
    private String register_info_type;
    private String register_info_by;
    private String identity_type;
}
