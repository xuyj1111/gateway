package xu.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xu.gateway.feign.AllFeign;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AllFeign allFeign;

    @PostMapping("/hello")
    public String hello() {
        return allFeign.helloWorld();
    }
}