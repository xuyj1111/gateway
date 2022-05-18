package xu.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: ribbon 配置类
 * 配置类不能放在被 @ComponentScan 注解能扫描到的包下，否则将被所有的 ribbon client共享，
 * 如有A、B、C三种服务，如果 ribbon 配置类被该注解扫描到了，我们原本只想让A服务自己来遵循该负载策略，
 * 结果我们所有的服务 A、B、C 全都按照这个策略来执行了，达不到独自配置的目的
 * @Author: xuyujun
 * @Date: 2022/5/18
 */
@Configuration
public class RibbonConfig {
    @Bean
    public IRule iRule(){
        return new RoundRobinRule();
    }
}
