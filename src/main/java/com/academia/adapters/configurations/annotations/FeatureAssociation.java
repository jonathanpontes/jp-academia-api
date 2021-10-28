package com.academia.adapters.configurations.annotations;

import com.academia.adapters.enums.MyFeature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface FeatureAssociation {
    MyFeature value();
}
