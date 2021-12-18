package com.tcgl.config.db;

import org.slf4j.helpers.MessageFormatter;

/**
 * 外部数据源枚举类
 */
public enum DbInfo {

    HF_WMS("mysql", "10.202.82.24", "5645", "epg_cqzn_5009", "cqznuser", "hdznuser"),
    NW_WMS("mysql", "10.10.11.160", "3306", "epg_cqzn_5009", "root", "123456"),
    TEST_WMS("mysql", "10.176.158.2", "3306", "epg_cqzn_5009", "root", "admin@123"),
    PROD_WMS("mysql", "cosmowms.khaos.cosmoplat.com", "5645", "epg_sddz_5008", "sdznuser", "hdznuser"),
    PB_ORW("mysql", "10.206.97.126", "3431", "orw", "mesuser8700", "OoN2!hamt00"),
    DB_CQM("mysqlnossl", "10.133.0.119", "3306", "zhikong_cqm", "sdzn@icds", "ICDS@sdzn8700"),
    DB_ESD("mariadb", "10.176.158.248", "3306", "sunyes-esd", "root", "root123");

    private final String type;

    private final String ip;

    private final String port;

    private final String schema;

    private final String username;

    private final String password;

    DbInfo(String type, String ip, String port, String schema, String username, String password) {
        this.type = type;
        this.ip = ip;
        this.port = port;
        this.schema = schema;
        this.username = username;
        this.password = password;
    }

    public String type() {
        return this.type;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    public String url() {
        String pattern = DbDriver.getUrlPattern(type);
        //新加判断（新库不支持SSL，加了默认的false，故新写了一个配置源，拼接参数做了修改）
        if (type.equalsIgnoreCase("mysqlnossl")){
            return format(pattern , ip, port, schema);
        }
        return format(pattern, type, ip, port, schema);

    }

    private String format(String pattern, Object... args) {
        return MessageFormatter.arrayFormat(pattern, args).getMessage();
    }
}
