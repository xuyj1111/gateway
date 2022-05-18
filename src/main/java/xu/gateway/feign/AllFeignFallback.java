package xu.gateway.feign;

import org.springframework.stereotype.Component;

/** 
 * @Description: feign 支持 hystrix 降级
 * 1. 需要在 application.yml 中配置feign: hystrix.enabled = true
 * 2. 在 AllFeign 中指定
 * 3. AllFeign 和 AllFeignFallback 的同名方法对应降级
 * @Author: xuyujun
 * @Date: 2022/5/18 
 */ 
@Component
public class AllFeignFallback implements AllFeign{
    @Override
    public String helloWorld() {
        return "hello world (from fallback)";
    }
}
