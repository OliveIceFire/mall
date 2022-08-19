package com.example.mall.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.mall.common.ApiRestResponse;
import com.example.mall.common.Constant;
import com.example.mall.exception.MallException;
import com.example.mall.exception.MallExceptionEnum;
import com.example.mall.model.entity.Category;
import com.example.mall.model.entity.User;
import com.example.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.LogRecord;

public class AdminFilter implements Filter {

    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(Constant.JWT_TOKEN);
        //        HttpSession session = request.getSession();
//        User currentUser = (User) session.getAttribute(Constant.MALL_USER);
        if (token == null) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n"
                    + "\"status\": 10007,\n"
                    + "\"msg\": \"NEED_JWT_TOKEN\"\n"
                    + "\"data\": null\n"
                    + "}");
            out.flush();
            out.close();
            return;
        }


        Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            UserFilter.currentUser.setId(jwt.getClaim(Constant.USER_ID).asInt());
            UserFilter.currentUser.setRole(jwt.getClaim(Constant.USER_ROLE).asInt());
            UserFilter.currentUser.setUsername(jwt.getClaim(Constant.USER_NAME).asString());
        } catch (TokenExpiredException e) {
            //token过期，抛出异常
            throw new MallException(MallExceptionEnum.TOKEN_EXPIRED);
        } catch (JWTDecodeException e) {
            //解码失败，抛出异常
            throw new MallException(MallExceptionEnum.TOKEN_WRONG);
        }


        boolean adminRole = userService.checkAdminRole(UserFilter.currentUser);
        if (!adminRole) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n"
                    + "\"status\": 10009,\n"
                    + "\"msg\": \"NEED_ADMIN\"\n"
                    + "\"data\": null\n"
                    + "}");
            out.flush();
            out.close();

        } else {

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
