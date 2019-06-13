package com.users.component.config.swagger;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置01
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    // 定义分隔符
    private static final String splitor = ";";
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).
                select()
//                .apis(RequestHandlerSelectors
//                        .basePackage("com.news.modules.article.controller"))
                //.apis(RequestHandlerSelectors.any())
                .apis(basePackage("com.users.modules.role.controller"+splitor+"com.users.modules.user.controller"+splitor+"com.common.entity.user"+splitor+"com.users.modules.pres.controller"+splitor+"com.common.entity.pres"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("Employee Management REST API")
                .contact(new Contact("Ramesh Fadatare", "www.szcrain.com", "server@szcrun.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.1")
                .build();
    }
    /***多路径支持***/
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
