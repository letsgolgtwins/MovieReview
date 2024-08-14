package com.movie.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 커스텀 어노테이션 : 직접 어노테이션을 만듦
@Target({ElementType.METHOD}) // 메소드의 위에만 붙을 수 있는 어노테이션 (예를 들면 Controller어노테이션은 매소드위에서는 못쓰고, RequestMapping 어노테이션은 두 곳에서 모두 쓸 수 있음.)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeTrace {

}
