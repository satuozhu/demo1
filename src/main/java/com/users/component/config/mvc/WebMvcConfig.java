package com.users.component.config.mvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.users.component.config.mvc.interceptor.RequestTimeConsumingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * WebMvcConfig配置02
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 添加拦截器，排出不拦截的静态资源
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestTimeConsumingInterceptor())
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/v2/api-docs")
                //.excludePathPatterns("/webjars/**")
        ;
    }

    /**
     * 添加可访问的静态资源
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
    }

    /**
     * 防止Long类型数据精确度丢失
     * 加了的话，前端修要指定传参数给后台，否则接收不到参数
     * 序列换成json时,将所有的long变成string
     * 因为js中得数字类型不能包含所有的java long值，long的精确度会丢失
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

}
