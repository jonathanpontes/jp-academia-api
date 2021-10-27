package com.algaworks.contato.adapters.configurations;

import com.algaworks.contato.ContatoApiApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ContatoApiApplication.class)
public class BeanConfiguration {
}
