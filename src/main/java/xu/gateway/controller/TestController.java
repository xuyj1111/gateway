package xu.gateway.controller;

import cn.hutool.core.util.RandomUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xu.gateway.feign.AllFeign;

import java.util.Objects;

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

    /**
     * @Description: hystrix熔断
     * 使用注解 @HystrixCommand，默认使用application.yml中的配置
     * 也可在注解上配置
     * 描述测试环境：使用wrk压力测试，wrk -t9 -c20 -d1s --latency http://127.0.0.1:8102/test/fuse
     * 在线程数、错误率都在配置以下时，抛出异常和超时会进去 fallbackMethod 中，即服务降级（fallbackMethod的入参需要与主方法一致）
     * 若线程数、错误率超过配置，直接进入 fallbackMethod，即服务熔断
     */
//    @HystrixCommand(fallbackMethod = "fuseBreakFailback", commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10")
//    })
    @HystrixCommand(fallbackMethod = "fuseBreakFailback")
    @RequestMapping("/fuse")
    public void testFuseBreak(@RequestParam(value = "number", required = false) Integer number) throws Exception {
        if (Objects.isNull(number)) {
            number = RandomUtil.randomInt(100);
        }
        if (number > 80) {
            throw new Exception("The number is too big.");
        }
        System.out.println("success, number is " + number);
    }

    private String timeoutFailback() {
        return "The system timed out. Please try again later.";
    }

    private String timeoutFailback2() {
        return "The system timed out. Please try again later.(2)";
    }

    /**
     * 服务降级时，number值为 testFuseBreak 方法的入参值（并非方法内赋值的值）
     * 服务熔断时，不会进入 testFuseBreak 方法，直接进入此方法
     */
    private void fuseBreakFailback(Integer number) {
        System.out.println("Concurrent excess or timeout or failure rate is excessive. number is " + number);
    }
}
