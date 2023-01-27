package com.getir.readingisgood.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * defines software component. such as service classes, repositories so on.
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SoftwareComponent {

  String name() default "";

  String label() default "";

  String[] technologies() default {};

  String description();

}

