package xu.gateway.zuul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xu.gateway.zuul.filter.TokenFilter;

@Configuration
public class FilterConfiguration {

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
