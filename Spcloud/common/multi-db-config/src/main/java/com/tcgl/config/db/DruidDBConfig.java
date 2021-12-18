package com.tcgl.config.db;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.cosmo.elemes.config.web.MyBatisPlusMetaObjectHandler;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Configuration
@MapperScan("com.cosmo.elemes.**.dao")
@EnableConfigurationProperties({MybatisPlusProperties.class})
@EnableTransactionManagement
public class DruidDBConfig {

    @Autowired
    private Environment env;
    @Resource
    private DbProperty dbProperty;
    @Resource
    private MybatisPlusInterceptor mybatisPlusInterceptor;
    @Resource
    private MybatisPlusProperties mybatisPlusProperties;
    @Resource
    private MyBatisPlusMetaObjectHandler myBatisPlusMetaObjectHandler;

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    @Bean // 声明其为Bean实例
//    @Primary // 在同样的DataSource中，首先使用被标注的DataSource
    @Qualifier("mainDataSource")
    public DataSource dataSource(DataSourceProperties properties) throws SQLException {
//
        HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
//        DruidDataSource datasource = new DruidDataSource();
//        // 基础连接信息
//        datasource.setUrl(dbProperty.getUrl());
//        datasource.setUsername(dbProperty.getUsername());
//        datasource.setPassword(dbProperty.getPassword());
//        datasource.setDriverClassName(dbProperty.getDriverClassName());
//        // 连接池连接信息
////        datasource.setInitialSize(dbProperty.getInitialSize());
////        datasource.setMinIdle(dbProperty.getMinIdle());
////        datasource.setMaxActive(dbProperty.getMaxActive());
////        datasource.setMaxWait(dbProperty.getMaxWait());
//        datasource.setPoolPreparedStatements(true); //是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
//        //  datasource.setConnectionProperties("oracle.net.CONNECT_TIMEOUT=6000;oracle.jdbc.ReadTimeout=60000");//对于耗时长的查询sql，会受限于ReadTimeout的控制，单位毫秒
//        datasource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");//对于耗时长的查询sql，会受限于ReadTimeout的控制，单位毫秒
//        datasource.setTestOnBorrow(true); //申请连接时执行validationQuery检测连接是否有效，这里建议配置为TRUE，防止取到的连接不可用
//        datasource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
//        String validationQuery = "select 1 from dual";
//        datasource.setValidationQuery(validationQuery); //用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
//        datasource.setFilters("stat,wall");//属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
//        datasource.setTimeBetweenEvictionRunsMillis(60000); //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//        datasource.setMinEvictableIdleTimeMillis(180000); //配置一个连接在池中最小生存的时间，单位是毫秒，这里配置为3分钟180000
//        datasource.setKeepAlive(true); //打开druid.keepAlive之后，当连接池空闲时，池中的minIdle数量以内的连接，空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作，即执行druid.validationQuery指定的查询SQL，一般为select * from dual，只要minEvictableIdleTimeMillis设置的小于防火墙切断连接时间，就可以保证当连接空闲时自动做保活检测，不会被防火墙切断
//        datasource.setRemoveAbandoned(true); //是否移除泄露的连接/超过时间限制是否回收。
//        datasource.setRemoveAbandonedTimeout(3600); //泄露连接的定义时间(要超过最大事务的处理时间)；单位为秒。这里配置为1小时
//        datasource.setLogAbandoned(true); // 移除泄露连接发生是是否记录日志
//        return datasource;


    }

    @Bean(name = "dynamicDataSource")
    @Qualifier("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(DataSourceProperties properties) throws SQLException {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource dataSource = dataSource(properties);
        dynamicDataSource.setDebug(false);
//        配置缺省的数据源
//         默认数据源配置 DefaultTargetDataSource
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        //额外数据源配置 TargetDataSources
        targetDataSources.put("mainDataSource", dataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    // TODO 参考#com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration优化此配置，目前还有些外部配置没进到这里面。
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProperties properties) throws Exception {
        String typeAliasesPackage = env.getProperty("mybatis-plus.type-aliases-package");
        String mapperLocations = env.getProperty("mybatis-plus.mapper-locations");
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dynamicDataSource(properties));
        factory.setVfs(SpringBootVFS.class);

        MybatisConfiguration configuration = mybatisPlusProperties.getConfiguration();
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setMapUnderscoreToCamelCase(env.getProperty("mybatis-plus.configuration.map-underscore-to-camel-case", Boolean.class));
//        configuration.setCacheEnabled(env.getProperty("mybatis-plus.configuration.cache-enabled", Boolean.class));
//        configuration.setCallSettersOnNulls(env.getProperty("mybatis-plus.configuration.call-setters-on-nulls", Boolean.class));
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setUseDeprecatedExecutor(false);
        factory.setConfiguration(configuration);
        GlobalConfig globalConfig = mybatisPlusProperties.getGlobalConfig();
        globalConfig.setMetaObjectHandler(myBatisPlusMetaObjectHandler);
        factory.setGlobalConfig(globalConfig);
        factory.setTypeAliasesPackage(typeAliasesPackage);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        factory.setPlugins(mybatisPlusInterceptor);
        return factory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }


    public static String setTypeAliasesPackage(String typeAliasesPackage)
    {
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        List<String> allResult = new ArrayList<String>();
        try
        {
            for (String aliasesPackage : typeAliasesPackage.split(","))
            {
                List<String> result = new ArrayList<String>();
                aliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
                org.springframework.core.io.Resource[] resources = resolver.getResources(aliasesPackage);
                if (resources != null && resources.length > 0)
                {
                    MetadataReader metadataReader = null;
                    for (org.springframework.core.io.Resource resource : resources)
                    {
                        if (resource.isReadable())
                        {
                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
                            try
                            {
                                result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                            }
                            catch (ClassNotFoundException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (result.size() > 0)
                {
                    HashSet<String> hashResult = new HashSet<String>(result);
                    allResult.addAll(hashResult);
                }
            }
            if (allResult.size() > 0)
            {
                typeAliasesPackage = String.join(",", (String[]) allResult.toArray(new String[0]));
            }
            else
            {
                throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return typeAliasesPackage;
    }
}
