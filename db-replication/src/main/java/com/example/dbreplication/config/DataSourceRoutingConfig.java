package com.example.dbreplication.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@Configuration
public class DataSourceRoutingConfig {

    @Primary
    @Bean
    @DependsOn("targetDataSource")
    public DataSource routingDataSource(@Qualifier("targetDataSource") final DataSource targetDataSource) {
        return new LazyConnectionDataSourceProxy(targetDataSource);
    }

    @Bean
    @DependsOn({"readDataSource", "writeDataSource"})
    public DataSource targetDataSource(@Qualifier("writeDataSource") final DataSource writeDataSource,
                                       @Qualifier("readDataSource") final DataSource readDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.WRITE, writeDataSource);
        targetDataSources.put(DataSourceType.READ, readDataSource);

        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        return routingDataSource;
    }
}
