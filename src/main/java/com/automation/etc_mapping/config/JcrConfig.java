package com.automation.etc_mapping.config;

import org.apache.jackrabbit.commons.JcrUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JcrConfig {

    @Bean
    public Repository repository() throws RepositoryException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("org.apache.jackrabbit.repository.uri", "http://localhost:4502/crx/server");

        return JcrUtils.getRepository(parameters);
    }

    @Bean
    public Session session(Repository repository) throws RepositoryException {
        return repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
    }
}