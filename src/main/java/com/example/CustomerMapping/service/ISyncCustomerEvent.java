package com.example.CustomerMapping.service;

public interface ISyncCustomerEvent {
    Runnable handler(String message );
}

