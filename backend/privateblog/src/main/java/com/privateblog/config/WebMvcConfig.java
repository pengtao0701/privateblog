package com.privateblog.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	/**
	 * 重写添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		 //白名单地址 注意：不要添加server.servlet.servcontext-path:的目录
        List<String> excludeUrl = Arrays.asList("/user/*",  "/swagger-resources/*", "/swagger-ui.html");
		// 添加自定义拦截器，并拦截对应 url"
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/*").excludePathPatterns(excludeUrl);
	}
	
	/**
     * 前后端跨域，解决session为空
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer2() {
        return new WebMvcConfigurer() {
            /**
             * 设置头 使可以跨域访问
             * @param registry
             * @since 4.2
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(ConstantProperties.origin)
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .maxAge(3600)
                        .allowCredentials(true);
            }
        };
    }
}