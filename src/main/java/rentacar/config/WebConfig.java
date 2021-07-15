package rentacar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rentacar.repository.AppuserRepository;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AppuserRepository _appuserRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(this._appuserRepository));
    }
}
