package com.example.playground.util.diUtil.scope;

import javax.inject.Scope;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The ChildFragmentScoped custom scoping annotation specifies that the lifespan of a dependency be
 * the same as that of a child Fragment. This is used to annotate dependencies that behave like a
 * singleton within the lifespan of a child Fragment
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ChildFragmentScoped {
}
