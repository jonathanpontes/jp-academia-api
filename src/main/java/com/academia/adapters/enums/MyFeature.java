package com.academia.adapters.enums;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum MyFeature implements Feature {

    //@EnableByDefault
    @Label("Primeira Feature")
    FEATURE_ONE,

    @Label("Segunda Feature")
    FEATURE_TWO;

    public boolean isActive(){
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
