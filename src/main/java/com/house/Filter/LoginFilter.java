package com.house.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void destroy() {
    }
    //前台过滤器
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        //获取请求的地址
        String uri = request.getRequestURI();
        //截取请求地址的最后一个/的后面部分,与可以操作的地址做判断
        String path = uri.substring(uri.lastIndexOf("/") + 1);
        //设置过滤器,当没有登陆的情况下无法进入管理页面,只能进行登录和注册操作
        if (path.equals("login.jsp")||path.equals("loginAction")
                ||path.equals("regs.jsp")||path.equals("checkName")||path.equals("reg")||path.equals("jquery-1.8.3.js")){
            chain.doFilter(req, resp);
        }else{
            //用户登录后同时session中有值才能进入管理页面
            HttpSession session = request.getSession();
            Object o = session.getAttribute("login");
            if (o!=null){
                chain.doFilter(req, resp);
            }else {
                response.sendRedirect("login.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
