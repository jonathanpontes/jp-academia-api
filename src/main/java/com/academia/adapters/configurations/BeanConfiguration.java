package com.academia.adapters.configurations;

import com.academia.AcademiaApiApplication;
import com.academia.application.ports.UsuarioRepository;
import com.academia.application.services.UsuarioServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AcademiaApiApplication.class)
public class BeanConfiguration {

    @Bean
    UsuarioServiceImpl usuarioServiceImpl(UsuarioRepository repository) {
        return new UsuarioServiceImpl(repository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
