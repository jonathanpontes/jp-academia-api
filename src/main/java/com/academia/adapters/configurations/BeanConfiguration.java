package com.academia.adapters.configurations;

import com.academia.AcademiaApiApplication;
import com.academia.application.ports.FileRepository;
import com.academia.application.ports.UserRepository;
import com.academia.application.services.FileServiceImpl;
import com.academia.application.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AcademiaApiApplication.class)
public class BeanConfiguration {

    @Bean
    UserServiceImpl userServiceImpl(UserRepository repository) {
        return new UserServiceImpl(repository);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // config to string json to class
        modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader());
        modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);

        return modelMapper;
    }

    @Bean
    FileServiceImpl fileServiceImpl(FileRepository fileRepository) {
        return new FileServiceImpl(fileRepository);
    }
}
