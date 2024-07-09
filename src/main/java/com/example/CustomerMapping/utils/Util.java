package com.example.CustomerMapping.utils;

import com.example.CustomerMapping.entity.CustomerInfomation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;

@Slf4j
public class Util {
    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> callFuncReturnResultSet(String sql, Connection conn, Object... params) {
        CallableStatement statement = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> res = new ArrayList<>();
        StringBuilder sqlStr = new StringBuilder();
        try {
            sqlStr.append("{? = call ");
            sqlStr.append(sql);
            sqlStr.append("}");
            statement = conn.prepareCall(sqlStr.toString());
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; ++i) {
                    statement.setObject(i + 2, params[i]);
                }
            }
            statement.execute();
            resultSet = (ResultSet) statement.getObject(1);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(metaData.getColumnName(i), resultSet.getObject(metaData.getColumnName(i)));
                }
                res.add(map);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return res;
    }

    public Map<Integer, Object> callFunction(Connection conn, String sql, Object... params) {
        Map<Integer, Object> map = new HashMap<>();
        StringBuilder sqlStr = new StringBuilder();
        CallableStatement cs = null;
        try {
            sqlStr.append("{? = call ");
            sqlStr.append(sql);
            sqlStr.append("}");

            String[] parts = sqlStr.toString().split("\\?");
            int count = parts.length - 1;
            cs = conn.prepareCall(sqlStr.toString());
            cs.registerOutParameter(1, java.sql.Types.VARCHAR);
            for (int i = 0; i < params.length; i++) {
                cs.setObject(i + 2, params[i]);
            }
            for (int i = 0; i < (count - params.length - 1); i++) {
                cs.registerOutParameter(params.length + (i + 2), java.sql.Types.VARCHAR);
            }
            cs.execute();
            map.put(1, cs.getObject(1));
            for (int i = 0; i < (count - params.length - 1); i++) {
                map.put(params.length + (i + 2), cs.getObject(params.length + (i + 2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(1, e.getMessage());
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    log.info("Param: {}, Sql: {}, Res: {}", objectMapper.writeValueAsString(params), sql, objectMapper.writeValueAsString(map));
                } catch (Exception e) {
                }
            }
        }
        return map;
    }

    public boolean validate(CustomerInfomation transactionInfo, String guid) {
        if (transactionInfo.getCustomerId() == null) {
            log.info("Guid: {} | TransactionID đang trống", guid);
            return false;
        }
        return true;
    }

    public Date convertToDate(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }
}


