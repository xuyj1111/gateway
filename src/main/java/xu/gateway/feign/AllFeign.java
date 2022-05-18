package xu.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "all", fallback = AllFeignFallback.class)
public interface AllFeign {

    @PostMapping("/api/all/hello")
    String helloWorld();
}
