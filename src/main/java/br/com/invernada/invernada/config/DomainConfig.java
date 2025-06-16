package br.com.invernada.invernada.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("br.com.invernada.invernada.domain")
@EnableJpaRepositories("br.com.invernada.invernada.repos")
@EnableTransactionManagement
public class DomainConfig {
}
