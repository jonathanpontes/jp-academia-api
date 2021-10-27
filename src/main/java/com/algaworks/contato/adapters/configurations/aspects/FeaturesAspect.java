package com.algaworks.contato.adapters.configurations.aspects;

import com.algaworks.contato.adapters.configurations.annotations.FeatureAssociation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class FeaturesAspect {

    @Around(
            "@within(featureAssociation) || @annotation(featureAssociation)"
    )
    public Object checkAspect(ProceedingJoinPoint joinPoint,
                              FeatureAssociation featureAssociation) throws Throwable {

        if (featureAssociation.value().isActive()) {
            return joinPoint.proceed();
        } else {
            log.info(
                    "Feature " + featureAssociation.value().name() + " is not enabled!");
            return null;
        }
    }
}