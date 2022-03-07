package xu.gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class TokenFilter extends ZuulFilter {

    /**
    * @Description: 过滤器类型
    */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /** 
    * @Description: 过滤器执行顺序
    */ 
    @Override
    public int filterOrder() {
        return 0;
    }

    /** 
    * @Description: 是否开启过滤
    */ 
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("token is null");
            requestContext.setResponseStatusCode(401);
        }
        return null;
    }
}
