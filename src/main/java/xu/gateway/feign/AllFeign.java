package xu.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "all")
@RequestMapping("/api/all")
public interface AllFeign {

    @PostMapping("/hello_world")
    String helloWorld();
}
