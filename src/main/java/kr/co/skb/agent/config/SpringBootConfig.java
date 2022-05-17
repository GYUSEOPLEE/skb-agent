package kr.co.skb.agent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySources({
    @PropertySource(value = "classpath:properties/info.properties", encoding = "UTF-8"),
    @PropertySource(value = "classpath:properties/path.properties", encoding = "UTF-8")
})
public class SpringBootConfig implements WebMvcConfigurer {

}