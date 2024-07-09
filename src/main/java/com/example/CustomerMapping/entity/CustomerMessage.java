package com.example.CustomerMapping.entity;

import com.example.CustomerMapping.entity.ldsdb.CustomerMapping;
import com.example.CustomerMapping.entity.viviet.CustomerTest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerMessage {
    public String table;
    @JsonProperty("op_type")
    public String opType;
    @JsonProperty("op_ts")
    public String opTs;
    public String current_ts;
    public String pos;
    public CustomerTest before;
    public CustomerMapping after;
}
