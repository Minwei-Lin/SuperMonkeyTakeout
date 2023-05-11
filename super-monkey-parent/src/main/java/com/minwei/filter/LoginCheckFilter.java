package com.minwei.filter;

import com.alibaba.fastjson.JSON;
import com.minwei.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: LoginCheckFilter
 * @Author linminwei
 * @Package com.minwei.filter
 * @Date 2023/5/11 13:17
 * @description: 过滤器：检查用户是否已经完成登录
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //1. 获取本次请求的URL
        String requestURI = request.getRequestURI();

        log.info("拦截到请求:{}",request.getRequestURI());

        // 定义不需要请求的URL
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
        };
        //2. 判断本次请求是否需要处理
        boolean check  = check(urls,requestURI);
        //3. 如无需处理，则直接放行
        if (check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);//放行
            return;
        }
        //4. 判断登录状态，如已登录，则直接放行
        if (request.getSession().getAttribute("employee")!=null){
            //说明已经登录，直接放行
            filterChain.doFilter(request,response);
            return;
        }
        //5. 如未登录则返回未登录的结果
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
        return;
//        log.info("拦截到请求:{}",request.getRequestURI());
//        filterChain.doFilter(request,response);
    }

    /**
     * 路径匹配，检查本次请求是否需要放行。通过封装一个check方法判断请求是否处理
     * @param urls
     * @param requestURL
     * @return
     */
    public boolean check(String[] urls,String requestURL){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url,requestURL);
            if (match){
                return true;
            }
        }
        return false;
    }
}
