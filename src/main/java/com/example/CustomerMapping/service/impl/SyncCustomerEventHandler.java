//package com.example.CustomerMapping.service.impl.;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lpb.viviet.sync.dto.CustomerMessage;
//import com.lpb.viviet.sync.model.CustomerInfomation;
//import com.lpb.viviet.sync.repository.CustomerInfoMappingRepository;
//import com.lpb.viviet.sync.service.ISyncCustomerEvent;
//import com.lpb.viviet.sync.utils.Util;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import com.example.CustomerMapping.service.ISyncCustomerEvent;
//import com.example.CustomerMapping.repo.CustomerInfoMappingRepository;
//import com.example.CustomerMapping.utils.Util;
//import com.example.CustomerMapping.entity.CustomerInfomation;
//
//@Service
//@Slf4j
//public class SyncCustomerEventHandler implements ISyncCustomerEvent {
//
//    @Autowired
//    private CustomerInfoMappingRepository customerInfoMappingRepository;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    Util util = new Util();
//    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    public Runnable handler(String message) {
//        return () -> {
//            long timeStart = System.currentTimeMillis();
//            String groupAuth = null;
//
//            String guid = "" + System.currentTimeMillis() + (new Random().nextInt(99999));
//            log.info("GUID: {} | Message: {}", guid, message);
//            try {
//                CustomerInfomation customerInfomation = convertMessage(message);
//                customerInfoMappingRepository.save(customerInfomation);
//
//            } catch (Exception e) {
//                log.error("Guid: " + guid + " | HandlerMessageOpenBanking Exception 1 : " + e.getMessage());
//            }
//        };
//    }
//
//    public CustomerInfomation convertMessage (String message) {
//        CustomerInfomation transactionInfo = new CustomerInfomation();
//        try {
//            CustomerMessage messageEwallet = objectMapper.readValue(message, CustomerMessage.class);
//            if (messageEwallet.getOpType() != null) {
//                transactionInfo.setCustomerId(messageEwallet.getAfter().getGuid());
//            }
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return transactionInfo;
//    }
//}
