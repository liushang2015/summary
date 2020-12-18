package com.liushang.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;


        //Origin, X-Requested-With, Content-Type, Accept, Authorization, Cache-Control, authorization, X_FILENAME, TK-Authorization

        //增加Access-Control-Allow-Origin头部信息支持浏览器中的跨域访问
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "PUT, POST, DELETE, OPTIONS, GET, HEAD");
        //httpResponse.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Cache-Control, authorization, X_FILENAME, TK-Authorization");

        if ((httpRequest).getMethod().equalsIgnoreCase("OPTIONS")) {
            httpResponse.setStatus(200);
            httpResponse.getOutputStream().flush();
        } else {
            chain.doFilter(httpRequest, httpResponse);
        }

    }

    @Override
    public void destroy() {

    }
}

