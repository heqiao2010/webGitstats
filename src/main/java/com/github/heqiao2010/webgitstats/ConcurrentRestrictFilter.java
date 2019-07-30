package com.github.heqiao2010.webgitstats;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 * API并发控制过滤器
 * Created by qiaohe
 * Date: 19-7-1 上午11:54
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "concurrentRestrictFilter")
public class ConcurrentRestrictFilter implements Filter {
    private Log log = LogFactory.getLog(getClass());

    private static final Integer MAX_CONCURRENT_NUM = 1;

    private static final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_NUM);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("before acquire: " + semaphore.availablePermits());
        if(!semaphore.tryAcquire()){
            if(response instanceof HttpServletResponse){
                HttpServletResponse res = (HttpServletResponse)response;
                res.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                res.sendError(HttpStatus.BAD_REQUEST.value(), "reach max current num");
            }
            return;
        }
        log.info("after acquire: " + semaphore.availablePermits());
        try{
            chain.doFilter(request, response);
        } finally {
            semaphore.release();
            log.info("release: " + semaphore.availablePermits());
        }
    }

    @Override
    public void destroy() {

    }
}
