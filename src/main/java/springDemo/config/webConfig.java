package springDemo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//webConfig.java
@Configuration
public class webConfig implements WebMvcConfigurer {


    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/templates/");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new LocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest req) {
                String lang = req.getParameter("lang");
                Locale locale = Locale.getDefault();
                if(!(lang == null || lang.isEmpty())){
                    locale = new Locale(lang.split("_")[0], lang.split("_")[1]);
                }
                return locale;
            }

            @Override
            public void setLocale(HttpServletRequest req, HttpServletResponse response, Locale locale) {

            }
        };
    }
}
