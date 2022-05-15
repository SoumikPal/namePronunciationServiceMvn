package com.hackathon.paymenttracker.namepronunciation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.core.ResponseDiagnostics;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

import io.micrometer.core.lang.Nullable;

@Configuration
@EnableConfigurationProperties(CosmosProperties.class)
@EnableCosmosRepositories(basePackages = "com.hackathon.paymenttracker.namepronunciation.repository")
@PropertySource("classpath:application.properties")
public class CosmosCrudAppConfiguration extends AbstractCosmosConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(CosmosCrudAppConfiguration.class);

    @Autowired
    private CosmosProperties properties;

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        DirectConnectionConfig directConnectionConfig = DirectConnectionConfig.getDefaultConfig();
        return new CosmosClientBuilder()
            .endpoint("https://pt-namedb.documents.azure.com:443/")
            .key("DCIOUexpPs2u8pVaf9sfIkBXSAKz9r5S6nJi8n31gkCYQZkn6Icy5qzd2vf3zIROqD8cZvkymcr9n1oA7iiixg==")
            .directMode(directConnectionConfig);
    }
    
    @Override
    protected String getDatabaseName() {
        return "EmployeeName";
    }

}
