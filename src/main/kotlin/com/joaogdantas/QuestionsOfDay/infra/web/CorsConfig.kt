package com.joaogdantas.QuestionsOfDay.infra.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.addAllowedOrigin("*")

        config.addAllowedMethod("*")

        config.addAllowedHeader("*")

        source.registerCorsConfiguration("/**", config)

        return CorsFilter(source)
    }
}