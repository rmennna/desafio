package com.fourd.desafio.configurer;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class PaginationConfiguration implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver pageHandle = new PageableHandlerMethodArgumentResolver();
        pageHandle.setFallbackPageable(PageRequest.of(0, 20));
        resolvers.add(pageHandle);
    }
}