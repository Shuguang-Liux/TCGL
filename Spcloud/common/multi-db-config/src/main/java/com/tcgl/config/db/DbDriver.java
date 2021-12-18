package com.tcgl.config.db;

public enum DbDriver {

    MYSQL("com.mysql.cj.jdbc.Driver",
            "jdbc:{}://{}:{}/{}?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8",
            "select 1 from dual",
            "mysqlInfoService"
            ),
    MYSQLNOSSL("com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://{}:{}/{}?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8",
            "select 1 from dual",
            "mysqlInfoService"
            ),
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:{}://{}:{};DatabaseName={}",
            "select 1",
            "sqlServerInfoService"),
    ORACLE("oracle.jdbc.OracleDriver",
            "jdbc:{}:thin:@{}:{}:{}",
            "SELECT * from dual",
            "oracleInfoService"),
    MARIADB("org.mariadb.jdbc.Driver",
            "jdbc:{}://{}:{}/{}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true",
            "SELECT 1 from dual",
            "mariadbInfoService");

    DbDriver(String driverClass, String urlPattern, String validationQuery, String serviceName) {
        this.driverClass = driverClass;
        this.urlPattern = urlPattern;
        this.validationQuery = validationQuery;
        this.serviceName = serviceName;
    }

    private final String driverClass;

    private final String urlPattern;

    private final String validationQuery;

    private final String serviceName;

    public static String getDriverClass(String name) {
        DbDriver[] drivers = DbDriver.values();
        String rs = MYSQL.driverClass;
        for (DbDriver dbDriver: drivers) {
            if (dbDriver.name().equalsIgnoreCase(name)) {
                rs = dbDriver.driverClass;
                break;
            }
        }
        return rs;
    }

    public static String getValidationQuery(String name) {
        DbDriver[] drivers = DbDriver.values();
        String rs = MYSQL.validationQuery;
        for (DbDriver dbDriver: drivers) {
            if (dbDriver.name().equalsIgnoreCase(name)) {
                rs = dbDriver.validationQuery;
                break;
            }
        }
        return rs;
    }

    public static DbDriver getEnum(String name) {
        DbDriver[] drivers = DbDriver.values();
        DbDriver rs = DbDriver.MYSQL;
        for (DbDriver dbDriver: drivers) {
            if (dbDriver.name().equalsIgnoreCase(name)) {
                rs = dbDriver;
                break;
            }
        }
        return rs;
    }

    public static String getServiceName(String name) {
        DbDriver[] drivers = DbDriver.values();
        String rs = MYSQL.serviceName;
        for (DbDriver dbDriver: drivers) {
            if (dbDriver.name().equalsIgnoreCase(name)) {
                rs = dbDriver.serviceName;
                break;
            }
        }
        return rs;
    }


    public static String getUrlPattern(String name) {
        DbDriver[] drivers = DbDriver.values();
        String rs = MYSQL.urlPattern;
        for (DbDriver dbDriver: drivers) {
            if (dbDriver.name().equalsIgnoreCase(name)) {
                rs = dbDriver.urlPattern;
                break;
            }
        }
        return rs;
    }

}
