package com.academia.adapters.configurations;

import com.academia.AcademiaApiApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AcademiaApiApplication.class)
public class BeanConfiguration {
}
