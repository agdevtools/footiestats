package com.footiestats

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Import(AspectConfig::class)
@Configuration
class AppConfig {

        @Bean
        fun corsFilter(): FilterRegistrationBean<*> {
            val source = UrlBasedCorsConfigurationSource()
            val config = CorsConfiguration()
            config.allowCredentials = true
            //config.addAllowedOrigin("http://localhost:3000")
            config.addAllowedOrigin("https://unitedappfrontend.herokuapp.com")
            config.addAllowedHeader("*")
            config.addAllowedMethod("*")
            source.registerCorsConfiguration("/**", config)
            val bean: FilterRegistrationBean<*> = FilterRegistrationBean(CorsFilter(source))
            bean.order = 0
            return bean
        }
}