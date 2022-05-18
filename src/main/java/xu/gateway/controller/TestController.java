package xu.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xu.gateway.feign.AllFeign;

@RestController
@RequestMapping("/test")
@DefaultProperties(defaultFallback = "timeoutFailback2")
public class TestController {

    @Autowired
    private AllFeign allFeign;

    @RequestMapping("/hello")
    public String hello() {
        return allFeign.helloWorld();
    }

    /**
     * @Description: hystrix降级（方式一：通过注解）
     */
    @HystrixCommand(fallbackMethod = "timeoutFailback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    @RequestMapping("/timeout")
    public String testTimeout() {

//        若报错，立刻降级
//        int a = 10 / 0;
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Did not time out, haha";
    }

    /**
     * @Description: hystrix降级
     * （方式二：通过 @DefaultProperties 指定当前类的默认方法，application.yml中配置全局超时时间）
     */
    @HystrixCommand
    @RequestMapping("/timeout2")
    public String testTimeout2() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Did not time out, haha";
    }

    private String timeoutFailback() {
        return "The system timed out. Please try again later.";
    }

    private String timeoutFailback2() {
        return "The system timed out. Please try again later.(2)";
    }
}
