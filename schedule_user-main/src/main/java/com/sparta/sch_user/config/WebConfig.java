package com.sparta.sch_user.config;

import com.sparta.sch_user.filter.LoginFilter; // 커스텀 필터 클래스 임포트
import jakarta.servlet.Filter; // Servlet API의 Filter 인터페이스 임포트
import org.springframework.boot.web.servlet.FilterRegistrationBean; // 스프링 부트에서 필터를 등록하기 위한 클래스 임포트
import org.springframework.context.annotation.Bean; // @Bean 애너테이션 사용을 위해 임포트
import org.springframework.context.annotation.Configuration; // @Configuration 애너테이션 사용을 위해 임포트

// 이 클래스는 스프링 부트 애플리케이션에서 필터를 설정하기 위한 구성 클래스입니다.
@Configuration
public class WebConfig {

    // @Bean: 스프링 컨테이너에 해당 메서드의 반환값을 Bean으로 등록합니다.
    @Bean
    public FilterRegistrationBean loginFilter() {
        // FilterRegistrationBean: 스프링 부트에서 필터를 등록하고 설정하기 위해 사용하는 클래스입니다.
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        // setFilter: 적용할 필터를 설정합니다. 여기서는 커스텀 필터인 LoginFilter를 사용합니다.
        filterRegistrationBean.setFilter(new LoginFilter());

        // setOrder: 필터의 실행 순서를 지정합니다. 숫자가 낮을수록 먼저 실행됩니다.
        filterRegistrationBean.setOrder(1);

        // addUrlPatterns: 필터가 적용될 URL 패턴을 설정합니다. 여기서는 모든 요청(`/*`)에 대해 필터를 적용합니다.
        filterRegistrationBean.addUrlPatterns("/*");

        // 설정이 완료된 FilterRegistrationBean을 반환하여 스프링 컨테이너에 등록합니다.
        return filterRegistrationBean;
    }
}
